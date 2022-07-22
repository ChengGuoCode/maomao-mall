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
 * 任务策略表
 * </p>
 *
 * @author gc
 * @since 2022-07-22
 */
@TableName("mao_task_strategy")
@ApiModel(value="TaskStrategyPO对象", description="任务策略表")
public class TaskStrategyPO implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "策略ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;


    @ApiModelProperty(value = "任务ID")
    @TableField("task_id")
    private Long taskId;


    @ApiModelProperty(value = "完成条件次数")
    @TableField("condition_val")
    private Integer conditionVal;


    @ApiModelProperty(value = "完成条件描述")
    @TableField("condition_desc")
    private String conditionDesc;


    @ApiModelProperty(value = "奖励类型 0-积分，1-商品")
    @TableField("reward_type")
    private Integer rewardType;


    @ApiModelProperty(value = "奖励积分")
    @TableField("reward_point")
    private Integer rewardPoint;


    @ApiModelProperty(value = "当天有效开始时间")
    @TableField("intra_start_time")
    private String intraStartTime;


    @ApiModelProperty(value = "当天有效结束时间")
    @TableField("intra_end_time")
    private String intraEndTime;


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

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public Integer getConditionVal() {
        return conditionVal;
    }

    public void setConditionVal(Integer conditionVal) {
        this.conditionVal = conditionVal;
    }

    public String getConditionDesc() {
        return conditionDesc;
    }

    public void setConditionDesc(String conditionDesc) {
        this.conditionDesc = conditionDesc;
    }

    public Integer getRewardType() {
        return rewardType;
    }

    public void setRewardType(Integer rewardType) {
        this.rewardType = rewardType;
    }

    public Integer getRewardPoint() {
        return rewardPoint;
    }

    public void setRewardPoint(Integer rewardPoint) {
        this.rewardPoint = rewardPoint;
    }

    public String getIntraStartTime() {
        return intraStartTime;
    }

    public void setIntraStartTime(String intraStartTime) {
        this.intraStartTime = intraStartTime;
    }

    public String getIntraEndTime() {
        return intraEndTime;
    }

    public void setIntraEndTime(String intraEndTime) {
        this.intraEndTime = intraEndTime;
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