package com.gdng.entity.task.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.baomidou.mybatisplus.annotation.*;

/**
 * <p>
 * 任务记录表
 * </p>
 *
 * @author gc
 * @since 2022-07-22
 */
@TableName("mao_task_record")
@ApiModel(value="TaskRecordPO对象", description="任务记录表")
public class TaskRecordPO implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "记录ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;


    @ApiModelProperty(value = "乐观锁")
    @TableField(value = "optimistic", fill = FieldFill.INSERT)
    @Version
    private Integer optimistic;


    @ApiModelProperty(value = "任务ID")
    @TableField("task_id")
    private Long taskId;


    @ApiModelProperty(value = "策略ID")
    @TableField("strategy_id")
    private Long strategyId;


    @ApiModelProperty(value = "执行次数")
    @TableField("times")
    private Integer times;


    @ApiModelProperty(value = "执行用户ID")
    @TableField("uid")
    private String uid;


    @ApiModelProperty(value = "任务完成状态 0-未完成，1-已完成")
    @TableField("complete_status")
    private Integer completeStatus;


    @ApiModelProperty(value = "完成时间")
    @TableField("complete_time")
    private Date completeTime;


    @ApiModelProperty(value = "奖励状态 0-等待下发，1-下发成功，2-下发失败")
    @TableField("reward_status")
    private Integer rewardStatus;


    @ApiModelProperty(value = "下发时间")
    @TableField("reward_time")
    private Date rewardTime;


    @ApiModelProperty(value = "失败原因")
    @TableField("fail_reason")
    private String failReason;


    @ApiModelProperty(value = "创建人")
    @TableField(value = "creator", fill = FieldFill.INSERT)
    private String creator;


    @ApiModelProperty(value = "创建时间")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;


    @ApiModelProperty(value = "更新人")
    @TableField(value = "updator", fill = FieldFill.INSERT_UPDATE)
    private String updator;


    @ApiModelProperty(value = "更新时间")
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getOptimistic() {
        return optimistic;
    }

    public void setOptimistic(Integer optimistic) {
        this.optimistic = optimistic;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public Long getStrategyId() {
        return strategyId;
    }

    public void setStrategyId(Long strategyId) {
        this.strategyId = strategyId;
    }

    public Integer getTimes() {
        return times;
    }

    public void setTimes(Integer times) {
        this.times = times;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Integer getCompleteStatus() {
        return completeStatus;
    }

    public void setCompleteStatus(Integer completeStatus) {
        this.completeStatus = completeStatus;
    }

    public Date getCompleteTime() {
        return completeTime;
    }

    public void setCompleteTime(Date completeTime) {
        this.completeTime = completeTime;
    }

    public Integer getRewardStatus() {
        return rewardStatus;
    }

    public void setRewardStatus(Integer rewardStatus) {
        this.rewardStatus = rewardStatus;
    }

    public Date getRewardTime() {
        return rewardTime;
    }

    public void setRewardTime(Date rewardTime) {
        this.rewardTime = rewardTime;
    }

    public String getFailReason() {
        return failReason;
    }

    public void setFailReason(String failReason) {
        this.failReason = failReason;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdator() {
        return updator;
    }

    public void setUpdator(String updator) {
        this.updator = updator;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }


}