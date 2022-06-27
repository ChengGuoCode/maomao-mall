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
 * 订单表
 * </p>
 *
 * @author gc
 * @since 2022-06-26
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@TableName("mao_order")
@ApiModel(value="OrderPO对象", description="订单表")
public class OrderPO implements Serializable {

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


    @ApiModelProperty(value = "订单状态 0-待支付，1-支付完成，2-已取消，3-部分退款，4-退款")
    @TableField("status")
    private Integer status;


    @ApiModelProperty(value = "订单金额")
    @TableField("order_price")
    private Long orderPrice;


    @ApiModelProperty(value = "支付金额")
    @TableField("payment")
    private Long payment;


    @ApiModelProperty(value = "支付方式 0-余额")
    @TableField("pay_way")
    private Integer payWay;


    @ApiModelProperty(value = "支付时间")
    @TableField("pay_time")
    private Date payTime;


    @ApiModelProperty(value = "付款单号")
    @TableField("pay_order_no")
    private String payOrderNo;


    @ApiModelProperty(value = "付款方UID")
    @TableField("payer_uid")
    private String payerUid;


    @ApiModelProperty(value = "订单来源 0-微信小程序")
    @TableField("order_source")
    private Integer orderSource;


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