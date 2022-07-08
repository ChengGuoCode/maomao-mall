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
 * 订单支付表
 * </p>
 *
 * @author gc
 * @since 2022-07-08
 */
@TableName("mao_order_pay")
@ApiModel(value="OrderPayPO对象", description="订单支付表")
public class OrderPayPO implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;


    @ApiModelProperty(value = "乐观锁")
    @TableField(value = "optimistic", fill = FieldFill.INSERT)
    @Version
    private Integer optimistic;


    @ApiModelProperty(value = "支付单号")
    @TableField("pay_no")
    private String payNo;


    @ApiModelProperty(value = "订单编号")
    @TableField("order_no")
    private String orderNo;


    @ApiModelProperty(value = "支付金额")
    @TableField("payment")
    private Long payment;


    @ApiModelProperty(value = "支付方式 0-余额")
    @TableField("pay_way")
    private Integer payWay;


    @ApiModelProperty(value = "付款账户")
    @TableField("pay_acc")
    private Long payAcc;


    @ApiModelProperty(value = "收款账户")
    @TableField("recipient_acc")
    private Long recipientAcc;


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

    public String getPayNo() {
        return payNo;
    }

    public void setPayNo(String payNo) {
        this.payNo = payNo;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Long getPayment() {
        return payment;
    }

    public void setPayment(Long payment) {
        this.payment = payment;
    }

    public Integer getPayWay() {
        return payWay;
    }

    public void setPayWay(Integer payWay) {
        this.payWay = payWay;
    }

    public Long getPayAcc() {
        return payAcc;
    }

    public void setPayAcc(Long payAcc) {
        this.payAcc = payAcc;
    }

    public Long getRecipientAcc() {
        return recipientAcc;
    }

    public void setRecipientAcc(Long recipientAcc) {
        this.recipientAcc = recipientAcc;
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