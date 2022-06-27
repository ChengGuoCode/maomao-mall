package com.gdng.entity.order.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 订单明细表
 * </p>
 *
 * @author gc
 * @since 2022-06-26
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
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


    @ApiModelProperty(value = "订单ID")
    @TableField("order_id")
    private Long orderId;


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


    @ApiModelProperty(value = "skuID")
    @TableField("sku_id")
    private Long skuId;


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


    @ApiModelProperty(value = "付款方UID")
    @TableField("payer_uid")
    private String payerUid;


    @ApiModelProperty(value = "收款方ID")
    @TableField("recipient_id")
    private String recipientId;


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



}