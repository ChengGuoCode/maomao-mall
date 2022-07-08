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
 * 订单支付明细表
 * </p>
 *
 * @author gc
 * @since 2022-07-08
 */
@TableName("mao_order_pay_detail")
@ApiModel(value="OrderPayDetailPO对象", description="订单支付明细表")
public class OrderPayDetailPO implements Serializable {

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
    @TableField("beneficiary")
    private Long beneficiary;


    @ApiModelProperty(value = "商家ID")
    @TableField("business_id")
    private Long businessId;


    @ApiModelProperty(value = "店铺ID")
    @TableField("store_id")
    private Long storeId;


    @ApiModelProperty(value = "商品ID")
    @TableField("product_id")
    private Long productId;


    @ApiModelProperty(value = "sku编码")
    @TableField("sku_code")
    private String skuCode;


    @ApiModelProperty(value = "商品sku价格")
    @TableField("price")
    private Long price;


    @ApiModelProperty(value = "数量")
    @TableField("num")
    private Integer num;


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

    public Long getBeneficiary() {
        return beneficiary;
    }

    public void setBeneficiary(Long beneficiary) {
        this.beneficiary = beneficiary;
    }

    public Long getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Long businessId) {
        this.businessId = businessId;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
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