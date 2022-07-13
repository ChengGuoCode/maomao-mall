package com.gdng.entity.order.po;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 订单明细表
 * </p>
 *
 * @author gc
 * @since 2022-06-26
 */
@TableName("mao_order_detail")
@ApiModel(value="OrderDetailPO对象", description="订单明细表")
public class OrderDetailPO implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "订单ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;


    @ApiModelProperty(value = "乐观锁")
    @TableField(value = "optimistic", fill = FieldFill.INSERT)
    @Version
    private Integer optimistic;


    @ApiModelProperty(value = "订单编号")
    @TableField("order_no")
    private String orderNo;


    @ApiModelProperty(value = "商家ID")
    @TableField("business_id")
    private Long businessId;


    @ApiModelProperty(value = "店铺ID")
    @TableField("store_id")
    private Long storeId;


    @ApiModelProperty(value = "商品ID")
    @TableField("product_id")
    private Long productId;


    @ApiModelProperty(value = "商品名称")
    @TableField("product_name")
    private String productName;


    @ApiModelProperty(value = "sku编码")
    @TableField("sku_code")
    private String skuCode;


    @ApiModelProperty(value = "商品类型")
    @TableField("product_type")
    private Integer productType;


    @ApiModelProperty(value = "店铺商品sku价格")
    @TableField("price")
    private Long price;


    @ApiModelProperty(value = "商品数量")
    @TableField("goods_num")
    private Integer goodsNum;


    @ApiModelProperty(value = "支付金额")
    @TableField("payment")
    private Long payment;


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

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
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

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode;
    }

    public Integer getProductType() {
        return productType;
    }

    public void setProductType(Integer productType) {
        this.productType = productType;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Integer getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(Integer goodsNum) {
        this.goodsNum = goodsNum;
    }

    public Long getPayment() {
        return payment;
    }

    public void setPayment(Long payment) {
        this.payment = payment;
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