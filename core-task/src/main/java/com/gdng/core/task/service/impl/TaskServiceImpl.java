package com.gdng.core.task.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gdng.core.task.dao.service.TaskDaoService;
import com.gdng.core.task.dao.service.TaskPrizeDaoService;
import com.gdng.core.task.dao.service.TaskRecordDaoService;
import com.gdng.core.task.dao.service.TaskStrategyDaoService;
import com.gdng.core.task.service.TaskService;
import com.gdng.entity.task.po.TaskPO;
import com.gdng.entity.task.po.TaskPrizePO;
import com.gdng.entity.task.po.TaskRecordPO;
import com.gdng.entity.task.po.TaskStrategyPO;
import com.gdng.inner.api.task.constant.RewardStrategyEnum;
import com.gdng.inner.api.task.constant.RewardTypeEnum;
import com.gdng.inner.api.task.constant.TaskTypeEnum;
import com.gdng.inner.api.task.dto.TaskDTO;
import com.gdng.inner.api.task.dto.TaskPrizeDTO;
import com.gdng.inner.api.task.dto.TaskStrategyDTO;
import com.gdng.support.common.constant.StatusConstant;
import com.gdng.support.common.dto.UserDTO;
import com.gdng.support.common.dto.res.GlobalResponseEnum;
import com.gdng.support.common.exception.GdngException;
import com.gdng.support.common.spring.SpringContextHolder;
import com.gdng.support.common.util.GdngBeanUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Auther: guocheng
 * @CreateDate: 2022/7/20 10:12
 * @Description:
 * @Version: 1.0.0
 */
@Service
public class TaskServiceImpl implements TaskService {

    private final TaskDaoService taskDaoService;
    private final TaskPrizeDaoService taskPrizeDaoService;
    private final TaskRecordDaoService taskRecordDaoService;
    private final TaskStrategyDaoService taskStrategyDaoService;

    @Autowired
    public TaskServiceImpl(TaskDaoService taskDaoService, TaskPrizeDaoService taskPrizeDaoService, TaskRecordDaoService taskRecordDaoService, TaskStrategyDaoService taskStrategyDaoService) {
        this.taskDaoService = taskDaoService;
        this.taskPrizeDaoService = taskPrizeDaoService;
        this.taskRecordDaoService = taskRecordDaoService;
        this.taskStrategyDaoService = taskStrategyDaoService;
    }

    @Override
    public void addOrUpdate(TaskDTO taskDTO) {
        checkTaskParam(taskDTO);

        TaskPO taskPO = GdngBeanUtil.copyToNewBean(taskDTO, TaskPO.class);
        taskDaoService.saveOrUpdate(taskPO);
        Long taskId = taskPO.getId();
        List<TaskStrategyDTO> strategyList = taskDTO.getStrategyList();
        List<TaskPrizePO> prizePOList = new ArrayList<>();
        strategyList.forEach(strategy -> {
            checkTaskStrategyParam(strategy);
            TaskStrategyPO taskStrategyPO = GdngBeanUtil.copyToNewBean(strategy, TaskStrategyPO.class);
            taskStrategyPO.setTaskId(taskId);
            taskStrategyDaoService.saveOrUpdate(taskStrategyPO);
            if (strategy.getRewardType() == RewardTypeEnum.GOODS.getType()) {
                List<TaskPrizeDTO> prizeList = strategy.getPrizeList();
                prizePOList.addAll(prizeList.stream().map(prize -> {
                    TaskPrizePO taskPrizePO = GdngBeanUtil.copyToNewBean(prize, TaskPrizePO.class);
                    taskPrizePO.setTaskId(taskId);
                    taskPrizePO.setStrategyId(taskStrategyPO.getId());
                    return taskPrizePO;
                }).collect(Collectors.toList()));
            }
        });
        if (!CollectionUtils.isEmpty(prizePOList)) {
            taskPrizeDaoService.saveOrUpdateBatch(prizePOList);
        }
    }

    @Override
    public int getTaskList(Integer taskType) {
        if (taskType == null || TaskTypeEnum.getTaskType(taskType) == null) {
            throw new GdngException(GlobalResponseEnum.BIZ_PARAM_ERR, "无效的任务类型");
        }
        UserDTO user = SpringContextHolder.getUser();
        if (user == null || StringUtils.isBlank(user.getId())) {
            throw new GdngException(GlobalResponseEnum.NO_LOGIN);
        }
        TaskTypeEnum taskTypeEnum = TaskTypeEnum.getTaskType(taskType);
        Date curDate = new Date();
        switch (taskTypeEnum) {
            case DAILY:
                List<TaskPO> dailyTaskList = taskDaoService.list(new QueryWrapper<TaskPO>().eq("reward_strategy", RewardStrategyEnum.LOOP.getStrategy())
                        .eq("status", StatusConstant.VALID)
                        .le("start_time", curDate)
                        .ge("end_time", curDate)
                        .orderByDesc("create_time"));
                if (CollectionUtils.isEmpty(dailyTaskList)) {
                    return 0;
                }
                List<Long> taskIdList = dailyTaskList.stream().map(TaskPO::getId).collect(Collectors.toList());
                List<TaskStrategyPO> strategyList = taskStrategyDaoService.list(new QueryWrapper<TaskStrategyPO>().in("task_id", taskIdList));
                if (CollectionUtils.isEmpty(strategyList)) {
                    return 0;
                }
                Map<Long, List<TaskStrategyPO>> taskStrategyMap = strategyList.stream().collect(Collectors.groupingBy(TaskStrategyPO::getTaskId));
                List<TaskRecordPO> recordList = taskRecordDaoService.list(new QueryWrapper<TaskRecordPO>().in("task_id", taskIdList)
                        .eq("creator", user.getId()));
                break;
            case COMMON:
                break;
            case HISTORY:
                break;
        }
        return 0;
    }

    private void checkTaskParam(TaskDTO taskDTO) {
        String name = taskDTO.getName();
        if (StringUtils.isBlank(name)) {
            throw new GdngException(GlobalResponseEnum.BIZ_PARAM_ERR, "任务名称不能为空");
        }

        Integer rewardStrategy = taskDTO.getRewardStrategy();
        if (rewardStrategy == null || RewardStrategyEnum.getStrategy(rewardStrategy) == null) {
            throw new GdngException(GlobalResponseEnum.BIZ_PARAM_ERR, "无效的奖励策略");
        }

        Integer limitTimes = taskDTO.getLimitTimes();
        if (limitTimes == null || limitTimes < 1) {
            taskDTO.setLimitTimes(1);
        }

        Date startTime = taskDTO.getStartTime();
        Date endTime = taskDTO.getEndTime();
        if (startTime == null || endTime == null) {
            throw new GdngException(GlobalResponseEnum.BIZ_PARAM_ERR, "任务开始时间、结束时间不能为空");
        }
        Date curDate = new Date();
        if (taskDTO.getId() != null && startTime.after(curDate)) {
            throw new GdngException(GlobalResponseEnum.BIZ_PARAM_ERR, "活动开始后，不可编辑");
        }
        if (startTime.before(curDate) || startTime.after(endTime)) {
            throw new GdngException(GlobalResponseEnum.BIZ_PARAM_ERR, "任务开始时间不能小于当前时间，大于结束时间");
        }

        List<TaskStrategyDTO> strategyList = taskDTO.getStrategyList();
        if (CollectionUtils.isEmpty(strategyList)) {
            throw new GdngException(GlobalResponseEnum.BIZ_PARAM_ERR, "任务策略配置不能为空");
        }
    }

    private void checkTaskStrategyParam(TaskStrategyDTO strategyDTO) {
        Integer conditionVal = strategyDTO.getConditionVal();
        if (conditionVal == null || conditionVal < 1) {
            throw new GdngException(GlobalResponseEnum.BIZ_PARAM_ERR, "无效的任务策略配置[完成次数]");
        }

        String conditionDesc = strategyDTO.getConditionDesc();
        if (StringUtils.isBlank(conditionDesc)) {
            throw new GdngException(GlobalResponseEnum.BIZ_PARAM_ERR, "无效的任务策略配置[完成条件]");
        }

        Integer rewardType = strategyDTO.getRewardType();
        if (rewardType == null || RewardTypeEnum.getRewardType(rewardType) == null) {
            throw new GdngException(GlobalResponseEnum.BIZ_PARAM_ERR, "无效的任务策略配置[奖励类型]");
        }

        Integer rewardPoint = strategyDTO.getRewardPoint();
        if (rewardType == RewardTypeEnum.POINT.getType() && (rewardPoint == null || rewardPoint < 1)) {
            throw new GdngException(GlobalResponseEnum.BIZ_PARAM_ERR, "无效的任务策略配置[奖励积分]");
        }

        String intraStartTime = strategyDTO.getIntraStartTime();
        String intraEndTime = strategyDTO.getIntraEndTime();
        if ((StringUtils.isNotBlank(intraStartTime) && intraStartTime.length() != 8) || (StringUtils.isNotBlank(intraEndTime) && intraEndTime.length() != 8)) {
            throw new GdngException(GlobalResponseEnum.BIZ_PARAM_ERR, "无效的任务策略配置[有效时段]");
        }

        if (rewardType == RewardTypeEnum.GOODS.getType() && CollectionUtils.isEmpty(strategyDTO.getPrizeList())) {
            throw new GdngException(GlobalResponseEnum.BIZ_PARAM_ERR, "奖励类型为商品时，奖品设置不能为空");
        }
    }
}
