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
 * 任务表
 * </p>
 *
 * @author gc
 * @since 2022-07-20
 */
@TableName("mao_task")
@ApiModel(value="TaskPO对象", description="任务表")
public class TaskPO implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "任务ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;


    @ApiModelProperty(value = "任务名称")
    @TableField("name")
    private String name;


    @ApiModelProperty(value = "奖励策略 0-循环，1-阶梯")
    @TableField("reward_strategy")
    private Integer rewardStrategy;


    @ApiModelProperty(value = "限制完成次数")
    @TableField("limit_times")
    private Integer limitTimes;


    @ApiModelProperty(value = "开始时间")
    @TableField("start_time")
    private Date startTime;


    @ApiModelProperty(value = "结束时间")
    @TableField("end_time")
    private Date endTime;


    @ApiModelProperty(value = "任务状态 0-正常，1-失效")
    @TableField("status")
    private Integer status;


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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getRewardStrategy() {
        return rewardStrategy;
    }

    public void setRewardStrategy(Integer rewardStrategy) {
        this.rewardStrategy = rewardStrategy;
    }

    public Integer getLimitTimes() {
        return limitTimes;
    }

    public void setLimitTimes(Integer limitTimes) {
        this.limitTimes = limitTimes;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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