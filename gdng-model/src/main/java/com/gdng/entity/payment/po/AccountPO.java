package com.gdng.entity.payment.po;

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
 * 支付账户表
 * </p>
 *
 * @author gc
 * @since 2022-07-08
 */
@TableName("mao_account")
@ApiModel(value="AccountPO对象", description="支付账户表")
public class AccountPO implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "账户ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;


    @ApiModelProperty(value = "乐观锁")
    @TableField(value = "optimistic", fill = FieldFill.INSERT)
    @Version
    private Integer optimistic;


    @ApiModelProperty(value = "账户类型 0-个人，1-平台，2-商家，3-店铺")
    @TableField("type")
    private Integer type;


    @ApiModelProperty(value = "类型关联ID")
    @TableField("corelation_id")
    private String corelationId;


    @ApiModelProperty(value = "账户余额")
    @TableField("balance")
    private Long balance;


    @ApiModelProperty(value = "支付密码")
    @TableField("pay_pass")
    private String payPass;


    @ApiModelProperty(value = "账户状态 0-正常，1-可支付不可提现，2-可提现不可支付，3-冻结")
    @TableField("acc_status")
    private Integer accStatus;


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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getCorelationId() {
        return corelationId;
    }

    public void setCorelationId(String corelationId) {
        this.corelationId = corelationId;
    }

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }

    public String getPayPass() {
        return payPass;
    }

    public void setPayPass(String payPass) {
        this.payPass = payPass;
    }

    public Integer getAccStatus() {
        return accStatus;
    }

    public void setAccStatus(Integer accStatus) {
        this.accStatus = accStatus;
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