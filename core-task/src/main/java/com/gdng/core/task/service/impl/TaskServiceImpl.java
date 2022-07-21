package com.gdng.core.task.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gdng.core.task.dao.service.*;
import com.gdng.core.task.service.TaskService;
import com.gdng.entity.task.po.TaskPO;
import com.gdng.entity.task.po.TaskPrizePO;
import com.gdng.entity.task.po.TaskRecordPO;
import com.gdng.entity.task.po.TaskStrategyPO;
import com.gdng.inner.api.task.constant.RewardStrategyEnum;
import com.gdng.inner.api.task.constant.RewardTypeEnum;
import com.gdng.inner.api.task.constant.TaskTypeEnum;
import com.gdng.inner.api.task.dto.*;
import com.gdng.support.common.dto.UserDTO;
import com.gdng.support.common.dto.res.GlobalResponseEnum;
import com.gdng.support.common.dto.res.PageResDTO;
import com.gdng.support.common.exception.GdngException;
import com.gdng.support.common.spring.SpringContextHolder;
import com.gdng.support.common.util.DateUtil;
import com.gdng.support.common.util.GdngBeanUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

import static com.gdng.support.common.constant.StringConstant.POUND;

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
    private final TaskRecordDetailDaoService taskRecordDetailDaoService;
    private final TaskStrategyDaoService taskStrategyDaoService;

    @Autowired
    public TaskServiceImpl(TaskDaoService taskDaoService, TaskPrizeDaoService taskPrizeDaoService, TaskRecordDaoService taskRecordDaoService, TaskRecordDetailDaoService taskRecordDetailDaoService, TaskStrategyDaoService taskStrategyDaoService) {
        this.taskDaoService = taskDaoService;
        this.taskPrizeDaoService = taskPrizeDaoService;
        this.taskRecordDaoService = taskRecordDaoService;
        this.taskRecordDetailDaoService = taskRecordDetailDaoService;
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
    public PageResDTO<TaskResDTO> getTaskList(TaskPageReqDTO reqDTO) {
        long pageNo = reqDTO.getPageNo();
        long pageSize = reqDTO.getPageSize();
        Integer taskType = reqDTO.getTaskType();
        if (taskType == null || TaskTypeEnum.getTaskType(taskType) == null) {
            throw new GdngException(GlobalResponseEnum.BIZ_PARAM_ERR, "无效的任务类型");
        }
        UserDTO user = SpringContextHolder.getUser();
        if (user == null || StringUtils.isBlank(user.getId())) {
            throw new GdngException(GlobalResponseEnum.NO_LOGIN);
        }
        PageResDTO<TaskResDTO> pageResDTO = new PageResDTO<>();
        pageResDTO.setPageNo(pageNo);
        pageResDTO.setPageSize(pageSize);

        TaskTypeEnum taskTypeEnum = TaskTypeEnum.getTaskType(taskType);
        Date curDate = new Date();
        switch (taskTypeEnum) {
            case DAILY:
                List<TaskPO> taskList = taskDaoService.list(new QueryWrapper<TaskPO>()
                        .select("id, name, create_time")
                        .eq("reward_strategy", RewardStrategyEnum.LOOP.getStrategy())
                        .eq("status", 0)
                        .le("start_time", curDate)
                        .ge("end_time", curDate)
                        .orderByDesc("create_time"));
                if (CollectionUtils.isEmpty(taskList)) {
                    return pageResDTO;
                }
                Map<Long, TaskPO> taskMap = taskList.stream().collect(Collectors.toMap(TaskPO::getId, e -> e));
                Set<Long> taskIds = taskMap.keySet();
                List<TaskStrategyPO> strategyList = taskStrategyDaoService.list(new QueryWrapper<TaskStrategyPO>().in("task_id", taskIds));
                if (CollectionUtils.isEmpty(strategyList)) {
                    return pageResDTO;
                }
                Map<Long, List<TaskStrategyPO>> taskStrategyMap = strategyList.stream().collect(Collectors.groupingBy(TaskStrategyPO::getTaskId));
                List<TaskRecordPO> recordList = taskRecordDaoService.list(new QueryWrapper<TaskRecordPO>().in("task_id", taskIds)
                        .eq("creator", user.getId())
                        .ge("create_time", DateUtil.getCurDateStart()));
                Map<String, TaskRecordPO> recordMap = new HashMap<>();
                if (!CollectionUtils.isEmpty(recordList)) {
                    recordMap.putAll(recordList.stream().collect(Collectors.toMap((e -> e.getTaskId() + POUND + e.getStrategyId()), e -> e)));
                }
                List<TaskResDTO> records = new ArrayList<>();
                taskStrategyMap.forEach((x, y) -> {
                    y.forEach(taskStrategy -> {
                        String intraStartTime = taskStrategy.getIntraStartTime();
                        String intraEndTime = taskStrategy.getIntraEndTime();
                        Date startDate = DateUtil.parseDateFromTime(intraStartTime);
                        Date endDate = DateUtil.parseDateFromTime(intraEndTime);
                        TaskRecordPO taskRecordPO = recordMap.get(x + POUND + taskStrategy.getId());
                        if ((startDate.before(curDate) && endDate.after(curDate)) && isNotComplete(taskRecordPO) != 2) {
                            TaskPO taskPO = taskMap.get(x);
                            TaskResDTO taskResDTO = new TaskResDTO();
                            taskResDTO.setName(taskPO.getName());
                            taskResDTO.setConditionDesc(taskStrategy.getConditionDesc());
                            taskResDTO.setRewardType(taskStrategy.getRewardType());
                            taskResDTO.setRewardPoint(taskStrategy.getRewardPoint());
                            taskResDTO.setTaskStatus(isNotComplete(taskRecordPO));
                            taskResDTO.setProgress((taskRecordPO == null ? 0 : taskRecordPO.getTimes()) + "/" + taskStrategy.getConditionVal());
                            taskResDTO.setCreateTime(taskPO.getCreateTime());
                            records.add(taskResDTO);
                        }
                    });
                });
                pageResDTO.setTotal(records.size());
                if (records.size() > 1) {
                    records.sort((o1, o2) -> {
                        if (o1.getTaskStatus() > o2.getTaskStatus()) {
                            return -1;
                        } else if (o1.getTaskStatus() < o2.getTaskStatus()) {
                            return 1;
                        } else {
                            if (o1.getCreateTime().after(o2.getCreateTime())) {
                                return -1;
                            } else if (o1.getCreateTime().before(o2.getCreateTime())) {
                                return 1;
                            } else {
                                return 0;
                            }
                        }
                    });
                    int fromIndex = Math.toIntExact((pageNo - 1) * pageSize);
                    int toIndex = Math.toIntExact(pageNo * pageSize);
                    if (fromIndex >= 0 && fromIndex <= records.size()) {
                        if (toIndex > records.size()) {
                            toIndex = records.size();
                        }
                        pageResDTO.setRecords(records.subList(fromIndex, toIndex));
                    } else {
                        pageResDTO.setRecords(new ArrayList<>());
                    }
                } else {
                    pageResDTO.setRecords(records);
                }
                break;
            case COMMON:
                break;
            case HISTORY:
                break;
        }
        return pageResDTO;
    }

    private int isNotComplete(TaskRecordPO taskRecordPO) {
        if (taskRecordPO == null) {
            return 0;
        }
        Integer completeStatus = taskRecordPO.getCompleteStatus();
        if (completeStatus == 0) {
            return 0;
        }
        Integer rewardStatus = taskRecordPO.getRewardStatus();
        if (rewardStatus == null) {
            return 0;
        }
        if (rewardStatus == 0) {
            return 1;
        }
        return 2;
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
