package com.gdng.core.task.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gdng.core.task.dao.service.*;
import com.gdng.core.task.mq.goods.GoodsSendProducer;
import com.gdng.core.task.mq.point.PointSendProducer;
import com.gdng.core.task.service.TaskService;
import com.gdng.entity.task.po.*;
import com.gdng.inner.api.task.constant.RewardStrategyEnum;
import com.gdng.inner.api.task.constant.RewardTypeEnum;
import com.gdng.inner.api.task.constant.TaskTypeEnum;
import com.gdng.inner.api.task.dto.*;
import com.gdng.inner.api.task.dto.mq.GoodsSendDTO;
import com.gdng.inner.api.task.dto.mq.GoodsSendItemDTO;
import com.gdng.inner.api.task.dto.mq.PointSendDTO;
import com.gdng.support.common.dto.UserDTO;
import com.gdng.support.common.dto.res.GlobalResponseEnum;
import com.gdng.support.common.dto.res.PageResDTO;
import com.gdng.support.common.exception.GdngException;
import com.gdng.support.common.spring.SpringContextHolder;
import com.gdng.support.common.util.ConvertUtil;
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
    private final PointSendProducer pointSendProducer;
    private final GoodsSendProducer goodsSendProducer;

    @Autowired
    public TaskServiceImpl(TaskDaoService taskDaoService, TaskPrizeDaoService taskPrizeDaoService, TaskRecordDaoService taskRecordDaoService, TaskRecordDetailDaoService taskRecordDetailDaoService, TaskStrategyDaoService taskStrategyDaoService, PointSendProducer pointSendProducer, GoodsSendProducer goodsSendProducer) {
        this.taskDaoService = taskDaoService;
        this.taskPrizeDaoService = taskPrizeDaoService;
        this.taskRecordDaoService = taskRecordDaoService;
        this.taskRecordDetailDaoService = taskRecordDetailDaoService;
        this.taskStrategyDaoService = taskStrategyDaoService;
        this.pointSendProducer = pointSendProducer;
        this.goodsSendProducer = goodsSendProducer;
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
            if (strategy.getRewardType() == RewardTypeEnum.GOODS.getType() || strategy.getRewardType() == RewardTypeEnum.MIX_P_G.getType()) {
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
        String uid = user.getId();
        switch (Objects.requireNonNull(taskTypeEnum)) {
            case DAILY:
                fillPageResult(pageResDTO, uid, RewardStrategyEnum.LOOP);
                break;
            case COMMON:
                fillPageResult(pageResDTO, uid, RewardStrategyEnum.STEP);
                break;
            case HISTORY:
                List<TaskRecordPO> taskRecordList = taskRecordDaoService.list(new QueryWrapper<TaskRecordPO>()
                        .select("task_id, strategy_id, complete_time")
                        .eq("uid", uid)
                        .eq("complete_status", 1)
                        .eq("reward_status", 1));
                if (!CollectionUtils.isEmpty(taskRecordList)) {
                    Map<Long, List<Long>> taskStrategyIdsMap = new HashMap<>();
                    Map<Long, Date> lastCompleteTimeMap = new HashMap<>();
                    taskRecordList.forEach(taskRecord -> {
                        Long taskId = taskRecord.getTaskId();
                        Long strategyId = taskRecord.getStrategyId();
                        List<Long> strategyIdList = taskStrategyIdsMap.get(taskId);
                        if (strategyIdList == null) {
                            strategyIdList = new ArrayList<>();
                            strategyIdList.add(strategyId);
                            taskStrategyIdsMap.put(taskId, strategyIdList);
                        } else {
                            strategyIdList.add(strategyId);
                        }
                        Date completeTime = lastCompleteTimeMap.get(taskId);
                        Date curCompleteTime = taskRecord.getCompleteTime();
                        if (completeTime == null) {
                            lastCompleteTimeMap.put(taskId, curCompleteTime);
                        } else {
                            if (completeTime.before(curCompleteTime)) {
                                lastCompleteTimeMap.put(taskId, curCompleteTime);
                            }
                        }
                    });
                    Set<Long> taskIds = taskStrategyIdsMap.keySet();
                    List<TaskPO> taskList = taskDaoService.list(new QueryWrapper<TaskPO>()
                            .in("id", taskIds)
                            .eq("reward_strategy", 1));
                    Map<Long, TaskPO> taskIdMap = taskList.stream().collect(Collectors.toMap(TaskPO::getId, e -> e));
                    Set<Long> stepTaskIdList = taskIdMap.keySet();
                    List<Map<String, Object>> strategyCountList = taskStrategyDaoService.listMaps(new QueryWrapper<TaskStrategyPO>()
                            .select("task_id taskId, sum(1) count")
                            .in("task_id", stepTaskIdList)
                            .groupBy("task_id"));
                    strategyCountList.forEach(strategyCountMap -> {
                        Long taskId = ConvertUtil.parseObjToLong(strategyCountMap.get("taskId"));
                        Integer count = ConvertUtil.parseObjToInt(strategyCountMap.get("count"));
                        List<Long> strategyIdList = taskStrategyIdsMap.get(taskId);
                        if (count != strategyIdList.size()) {
                            TaskPO taskPO = taskIdMap.get(taskId);
                            if (taskPO.getStatus() == 0 && new Date().before(taskPO.getEndTime())) {
                                taskIdMap.remove(taskId);
                            }
                        }
                    });
                    List<TaskPO> completeTaskList = new ArrayList<>(taskIdMap.values());
                    int total = completeTaskList.size();
                    pageResDTO.setTotal(total);
                    if (total > 1) {
                        completeTaskList.sort((o1, o2) -> {
                            Date lastTime1 = lastCompleteTimeMap.get(o1.getId());
                            Date lastTime2 = lastCompleteTimeMap.get(o2.getId());
                            if (lastTime1.after(lastTime2)) {
                                return -1;
                            } else if (lastTime1.before(lastTime2)) {
                                return 1;
                            } else {
                                return 0;
                            }
                        });
                        int fromIndex = Math.toIntExact((pageNo - 1) * pageSize);
                        int toIndex = Math.toIntExact(pageNo * pageSize);
                        if (fromIndex >= 0 && fromIndex < total) {
                            if (toIndex > total) {
                                toIndex = total;
                            }
                            pageResDTO.setRecords(transferTaskRes(completeTaskList.subList(fromIndex, toIndex)));
                        } else {
                            pageResDTO.setRecords(new ArrayList<>());
                        }
                    } else {
                        pageResDTO.setRecords(transferTaskRes(completeTaskList));
                    }
                }
                break;
        }
        return pageResDTO;
    }

    @Override
    public void execute(TaskExecuteReqDTO reqDTO) {
        checkExeParam(reqDTO);
        Long taskId = reqDTO.getTaskId();
        TaskPO task = taskDaoService.getById(taskId);
        if (task == null) {
            throw new GdngException(GlobalResponseEnum.BIZ_PARAM_ERR, "无效的任务ID");
        }
        String curTimeStr = DateUtil.getCurTimeStr();
        List<TaskStrategyPO> taskStrategyList = taskStrategyDaoService.list(new QueryWrapper<TaskStrategyPO>()
                .eq("task_id", taskId)
                .le("intra_start_time", curTimeStr)
                .ge("intra_end_time", curTimeStr)
                .orderByAsc("condition_val"));
        if (CollectionUtils.isEmpty(taskStrategyList)) {
            throw new GdngException(GlobalResponseEnum.BIZ_PARAM_ERR, "不在任务有效时段");
        }
        List<Long> strategyIdList = taskStrategyList.stream().map(TaskStrategyPO::getId).collect(Collectors.toList());
        String uid = reqDTO.getUid();
        List<TaskRecordPO> taskRecordList = taskRecordDaoService.list(new QueryWrapper<TaskRecordPO>()
                .eq("task_id", taskId)
                .in("strategy_id", strategyIdList)
                .eq("uid", uid));
        Map<Long, TaskRecordPO> recordMap = new HashMap<>();
        if (!CollectionUtils.isEmpty(taskRecordList)) {
            recordMap.putAll(taskRecordList.stream().collect(Collectors.toMap(TaskRecordPO::getStrategyId, e -> e)));
        }
        Integer times = reqDTO.getTimes();
        Date completeTime = new Date();
        int baseTimes = 0;
        for (TaskStrategyPO taskStrategy : taskStrategyList) {
            Long strategyId = taskStrategy.getId();
            TaskRecordPO taskRecord = recordMap.get(strategyId);
            Integer conditionVal = taskStrategy.getConditionVal();
            if (taskRecord == null) {
                int curTotalTimes = times + baseTimes;
                if (curTotalTimes < conditionVal) {
                    saveTaskRecord(taskId, strategyId, times, curTotalTimes, uid, 0, null, null);
                    return;
                } else if (curTotalTimes == conditionVal){
                    saveTaskRecord(taskId, strategyId, times, curTotalTimes, uid, 1, completeTime, 0);
                    sendTaskAward(taskId, strategyId, uid);
                    return;
                } else {
                    saveTaskRecord(taskId, strategyId, conditionVal - baseTimes, conditionVal, uid, 1, completeTime, 0);
                    sendTaskAward(taskId, strategyId, uid);
                }
            } else {
                Integer completeStatus = taskRecord.getCompleteStatus();
                if (completeStatus == 0) {
                    int curTotalTimes = times + taskRecord.getTimes();
                    if (curTotalTimes < conditionVal) {
                        updateTaskRecord(taskRecord, times, 0, null, null);
                    } else if (curTotalTimes == conditionVal) {
                        updateTaskRecord(taskRecord, times, 1, completeTime, 0);
                        sendTaskAward(taskId, strategyId, uid);
                        return;
                    } else {
                        updateTaskRecord(taskRecord, conditionVal - taskRecord.getTimes(), 1, completeTime, 0);
                        sendTaskAward(taskId, strategyId, uid);
                    }
                } else {
                    baseTimes += taskStrategy.getConditionVal();
                }
            }
        }
    }

    @Override
    public void rewardFallback(RewardFallbackReqDTO reqDTO) {
        checkRewardFallbackParam(reqDTO);
        TaskRecordPO taskRecord = taskRecordDaoService.getOne(new QueryWrapper<TaskRecordPO>()
                .eq("task_id", reqDTO.getTaskId())
                .eq("strategy_id", reqDTO.getStrategyId())
                .eq("uid", reqDTO.getUid()));
        if (taskRecord == null) {
            throw new GdngException(GlobalResponseEnum.BIZ_PARAM_ERR, "奖励下发关联的任务策略不存在");
        }
        taskRecord.setRewardStatus(reqDTO.getRewardStatus());
        taskRecord.setRewardTime(reqDTO.getRewardTime());
        taskRecord.setFailReason(reqDTO.getFailReason());
        taskRecordDaoService.updateById(taskRecord);
    }

    @Override
    public void receivePrize(RewardReceiveReqDTO reqDTO) {
        Long taskId = reqDTO.getTaskId();
        Long strategyId = reqDTO.getStrategyId();
        String uid = reqDTO.getUid();
        TaskRecordPO taskRecordPO = taskRecordDaoService.getOne(new QueryWrapper<TaskRecordPO>()
                .eq("task_id", taskId)
                .eq("strategy_id", strategyId)
                .eq("uid", uid));
        if (taskRecordPO.getCompleteStatus() == 1 && taskRecordPO.getRewardStatus() == 0) {
            taskRecordPO.setRewardStatus(1);
            taskRecordPO.setRewardTime(new Date());
            taskRecordDaoService.updateById(taskRecordPO);
        }
    }

    private void checkRewardFallbackParam(RewardFallbackReqDTO reqDTO) {
        Long taskId = reqDTO.getTaskId();
        Long strategyId = reqDTO.getStrategyId();
        if (taskId == null || strategyId == null) {
            throw new GdngException(GlobalResponseEnum.PARAM_ERR, "任务ID与策略ID不能为空");
        }
        String uid = reqDTO.getUid();
        if (StringUtils.isBlank(uid)) {
            throw new GdngException(GlobalResponseEnum.PARAM_ERR, "用户ID不能为空");
        }
        Integer rewardStatus = reqDTO.getRewardStatus();
        Date rewardTime = reqDTO.getRewardTime();
        if (rewardStatus == null || rewardTime == null) {
            throw new GdngException(GlobalResponseEnum.PARAM_ERR, "任务奖励下发状态与时间不能为空");
        }
    }

    private void updateTaskRecord(TaskRecordPO taskRecord, Integer times, Integer completeStatus,
                                  Date completeTime, Integer rewardStatus) {
        taskRecord.setTimes(taskRecord.getTimes() + times);
        taskRecord.setCompleteStatus(completeStatus);
        taskRecord.setCompleteTime(completeTime);
        taskRecord.setRewardStatus(rewardStatus);
        taskRecordDaoService.updateById(taskRecord);
        TaskRecordDetailPO taskRecordDetailPO = new TaskRecordDetailPO();
        taskRecordDetailPO.setTaskId(taskRecord.getTaskId());
        taskRecordDetailPO.setStrategyId(taskRecord.getStrategyId());
        taskRecordDetailPO.setRecordId(taskRecord.getId());
        taskRecordDetailPO.setSingleTimes(times);
        taskRecordDetailDaoService.save(taskRecordDetailPO);
    }

    private void sendTaskAward(Long taskId, Long strategyId, String uid) {
        TaskStrategyPO taskStrategy = taskStrategyDaoService.getById(strategyId);
        RewardTypeEnum rewardTypeEnum = RewardTypeEnum.getRewardType(taskStrategy.getRewardType());
        switch (Objects.requireNonNull(rewardTypeEnum)) {
            case POINT:
                sendPoint(taskId, strategyId, uid, taskStrategy);
                break;
            case GOODS:
                sendGoods(taskId, strategyId, uid);
                break;
            case MIX_P_G:
                sendPoint(taskId, strategyId, uid, taskStrategy);
                sendGoods(taskId, strategyId, uid);
                break;
        }
    }

    private void sendGoods(Long taskId, Long strategyId, String uid) {
        List<TaskPrizePO> taskPrizeList = taskPrizeDaoService.list(new QueryWrapper<TaskPrizePO>()
                .eq("task_id", taskId)
                .eq("strategy_id", strategyId));
        if (CollectionUtils.isEmpty(taskPrizeList)) {
            throw new GdngException(GlobalResponseEnum.BIZ_PARAM_ERR, "任务赠送奖品策略异常，请联系客服");
        }
        GoodsSendDTO goodsSendDTO = new GoodsSendDTO();
        goodsSendDTO.setTaskId(taskId);
        goodsSendDTO.setStrategyId(strategyId);
        goodsSendDTO.setUid(uid);
        goodsSendDTO.setGoodsItemList(taskPrizeList.stream().map(taskPrize -> GdngBeanUtil.copyToNewBean(taskPrize, GoodsSendItemDTO.class)).collect(Collectors.toList()));
        goodsSendProducer.sendMsg(goodsSendDTO);
    }

    private void sendPoint(Long taskId, Long strategyId, String uid, TaskStrategyPO taskStrategy) {
        Integer rewardPoint = taskStrategy.getRewardPoint();
        PointSendDTO pointSendDTO = new PointSendDTO();
        pointSendDTO.setTaskId(taskId);
        pointSendDTO.setStrategyId(strategyId);
        pointSendDTO.setPoint(rewardPoint);
        pointSendDTO.setBeneficiaryUid(uid);
        pointSendProducer.sendMsg(pointSendDTO);
    }

    private void saveTaskRecord(Long taskId, Long strategyId, Integer times, int curTotalTimes, String uid, Integer completeStatus,
                                Date completeTime, Integer rewardStatus) {
        TaskRecordPO taskRecordPO = new TaskRecordPO();
        taskRecordPO.setTaskId(taskId);
        taskRecordPO.setStrategyId(strategyId);
        taskRecordPO.setTimes(curTotalTimes);
        taskRecordPO.setUid(uid);
        taskRecordPO.setCompleteStatus(completeStatus);
        taskRecordPO.setCompleteTime(completeTime);
        taskRecordPO.setRewardStatus(rewardStatus);
        taskRecordDaoService.save(taskRecordPO);
        TaskRecordDetailPO taskRecordDetailPO = new TaskRecordDetailPO();
        taskRecordDetailPO.setTaskId(taskId);
        taskRecordDetailPO.setStrategyId(strategyId);
        taskRecordDetailPO.setRecordId(taskRecordPO.getId());
        taskRecordDetailPO.setSingleTimes(times);
        taskRecordDetailDaoService.save(taskRecordDetailPO);
    }

    private void checkExeParam(TaskExecuteReqDTO reqDTO) {
        Long taskId = reqDTO.getTaskId();
        if (taskId == null) {
            throw new GdngException(GlobalResponseEnum.PARAM_ERR, "任务ID不能为空");
        }
        String uid = reqDTO.getUid();
        if (StringUtils.isBlank(uid)) {
            throw new GdngException(GlobalResponseEnum.PARAM_ERR, "用户ID不能为空");
        }
        Integer times = reqDTO.getTimes();
        if (times == null || times < 1) {
            throw new GdngException(GlobalResponseEnum.PARAM_ERR, "无效的执行次数");
        }
    }

    private List<TaskResDTO> transferTaskRes(List<TaskPO> taskList) {
        if (CollectionUtils.isEmpty(taskList)) {
            return new ArrayList<>();
        }
        return taskList.stream().map(task -> {
            TaskResDTO taskResDTO = new TaskResDTO();
            taskResDTO.setName(task.getName());
            return taskResDTO;
        }).collect(Collectors.toList());
    }

    private void fillPageResult(PageResDTO<TaskResDTO> pageResDTO, String uid, RewardStrategyEnum strategy) {
        Date curDate = new Date();
        List<TaskPO> taskList = taskDaoService.list(new QueryWrapper<TaskPO>()
                .select("id, name, create_time")
                .eq("reward_strategy", strategy.getStrategy())
                .eq("status", 0)
                .le("start_time", curDate)
                .ge("end_time", curDate)
                .orderByDesc("create_time"));
        if (CollectionUtils.isEmpty(taskList)) {
            return;
        }
        Map<Long, TaskPO> taskMap = taskList.stream().collect(Collectors.toMap(TaskPO::getId, e -> e));
        Set<Long> taskIds = taskMap.keySet();
        List<TaskStrategyPO> strategyList = taskStrategyDaoService.list(new QueryWrapper<TaskStrategyPO>().in("task_id", taskIds));
        if (CollectionUtils.isEmpty(strategyList)) {
            return;
        }
        Map<Long, List<TaskStrategyPO>> taskStrategyMap = strategyList.stream().collect(Collectors.groupingBy(TaskStrategyPO::getTaskId));
        List<TaskRecordPO> recordList = taskRecordDaoService.list(new QueryWrapper<TaskRecordPO>().in("task_id", taskIds)
                .eq("uid", uid)
                .ge(strategy == RewardStrategyEnum.LOOP, "create_time", DateUtil.getCurDateStart()));
        Map<String, TaskRecordPO> recordMap = new HashMap<>();
        if (!CollectionUtils.isEmpty(recordList)) {
            recordMap.putAll(recordList.stream().collect(Collectors.toMap((e -> e.getTaskId() + POUND + e.getStrategyId()), e -> e)));
        }
        Map<Long, TaskResDTO> taskResMap = new HashMap<>();
        taskStrategyMap.forEach((x, y) -> {
            y.forEach(taskStrategy -> {
                String intraStartTime = taskStrategy.getIntraStartTime();
                String intraEndTime = taskStrategy.getIntraEndTime();
                Date startDate = DateUtil.parseDateFromTime(intraStartTime);
                Date endDate = DateUtil.parseDateFromTime(intraEndTime);
                TaskRecordPO taskRecordPO = recordMap.get(x + POUND + taskStrategy.getId());
                if (strategy == RewardStrategyEnum.LOOP && (getTaskStatus(taskRecordPO) == 1 || (startDate.before(curDate) && endDate.after(curDate) && getTaskStatus(taskRecordPO) == 0))
                            || strategy == RewardStrategyEnum.STEP && getTaskStatus(taskRecordPO) != 2) {
                    TaskResDTO taskResDTO = taskResMap.get(x);
                    if (taskResDTO == null) {
                        TaskPO taskPO = taskMap.get(x);
                        taskResDTO = new TaskResDTO();
                        taskResDTO.setName(taskPO.getName());
                        fillTaskResDTO(taskResDTO, taskStrategy, taskRecordPO);
                        taskResDTO.setCreateTime(taskPO.getCreateTime());
                        taskResMap.put(x, taskResDTO);
                    } else {
                        if (taskStrategy.getConditionVal() < taskResDTO.getConditionVal()) {
                            fillTaskResDTO(taskResDTO, taskStrategy, taskRecordPO);
                        }
                    }
                }
            });
        });
        List<TaskResDTO> records = new ArrayList<>(taskResMap.values());
        int total = records.size();
        pageResDTO.setTotal(total);
        if (total > 1) {
            records.sort((o1, o2) -> {
                Integer taskStatus1 = o1.getTaskStatus();
                Integer taskStatus2 = o2.getTaskStatus();
                if (taskStatus1 > taskStatus2) {
                    return -1;
                } else if (taskStatus1 < taskStatus2) {
                    return 1;
                } else {
                    Date createTime1 = o1.getCreateTime();
                    Date createTime2 = o2.getCreateTime();
                    if (createTime1.after(createTime2)) {
                        return -1;
                    } else if (createTime1.before(createTime2)) {
                        return 1;
                    } else {
                        return 0;
                    }
                }
            });
            long pageNo = pageResDTO.getPageNo();
            long pageSize = pageResDTO.getPageSize();
            int fromIndex = Math.toIntExact((pageNo - 1) * pageSize);
            int toIndex = Math.toIntExact(pageNo * pageSize);
            if (fromIndex >= 0 && fromIndex < total) {
                if (toIndex > total) {
                    toIndex = total;
                }
                pageResDTO.setRecords(records.subList(fromIndex, toIndex));
            } else {
                pageResDTO.setRecords(new ArrayList<>());
            }
        } else {
            pageResDTO.setRecords(records);
        }
    }

    private void fillTaskResDTO(TaskResDTO taskResDTO, TaskStrategyPO taskStrategy, TaskRecordPO taskRecordPO) {
        taskResDTO.setConditionDesc(taskStrategy.getConditionDesc());
        taskResDTO.setRewardType(taskStrategy.getRewardType());
        taskResDTO.setRewardPoint(taskStrategy.getRewardPoint());
        taskResDTO.setTaskStatus(getTaskStatus(taskRecordPO));
        taskResDTO.setConditionVal(taskStrategy.getConditionVal());
        taskResDTO.setProgress((taskRecordPO == null ? 0 : taskRecordPO.getTimes()) + "/" + taskStrategy.getConditionVal());
    }

    private int getTaskStatus(TaskRecordPO taskRecordPO) {
        if (taskRecordPO == null || taskRecordPO.getCompleteStatus() == 0) {
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
