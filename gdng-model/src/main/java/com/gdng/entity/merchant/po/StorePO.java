package com.gdng.entity.merchant.po;

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
 * 店铺表
 * </p>
 *
 * @author gc
 * @since 2022-06-26
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@TableName("mao_store")
@ApiModel(value="StorePO对象", description="店铺表")
public class StorePO implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "店铺ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;


    @ApiModelProperty(value = "店铺编码")
    @TableField("store_code")
    private String storeCode;


    @ApiModelProperty(value = "商家ID")
    @TableField("business_id")
    private Long businessId;


    @ApiModelProperty(value = "店铺名称")
    @TableField("name")
    private String name;


    @ApiModelProperty(value = "店铺类型")
    @TableField("store_type")
    private Integer storeType;


    @ApiModelProperty(value = "店铺电话")
    @TableField("mobile")
    private String mobile;


    @ApiModelProperty(value = "类目ID")
    @TableField("category_id")
    private Long categoryId;


    @ApiModelProperty(value = "店铺地址")
    @TableField("address")
    private String address;


    @ApiModelProperty(value = "店铺描述")
    @TableField("store_desc")
    private String storeDesc;


    @ApiModelProperty(value = "店铺LOGO")
    @TableField("logo")
    private String logo;


    @ApiModelProperty(value = "店铺主页背景图")
    @TableField("back_img")
    private String backImg;


    @ApiModelProperty(value = "店铺邮箱")
    @TableField("email")
    private String email;


    @ApiModelProperty(value = "店铺状态 0-待激活，1-运营中，2-禁用")
    @TableField("status")
    private Integer status;


    @ApiModelProperty(value = "拒绝原因")
    @TableField("refuse_reason")
    private String refuseReason;


    @ApiModelProperty(value = "审核人")
    @TableField("auditor")
    private String auditor;


    @ApiModelProperty(value = "审核时间")
    @TableField("audit_time")
    private Date auditTime;


    @ApiModelProperty(value = "收款账号")
    @TableField("receive_account_id")
    private String receiveAccountId;


    @ApiModelProperty(value = "是否支持外送 0-不支持，1-支持")
    @TableField("delivery")
    private Integer delivery;


    @ApiModelProperty(value = "收藏数量")
    @TableField("favorite_num")
    private Long favoriteNum;


    @ApiModelProperty(value = "营业开始时间")
    @TableField("open_start_time")
    private String openStartTime;


    @ApiModelProperty(value = "营业结束时间")
    @TableField("open_end_time")
    private String openEndTime;


    @ApiModelProperty(value = "经度")
    @TableField("longitude")
    private String longitude;


    @ApiModelProperty(value = "纬度")
    @TableField("latitude")
    private String latitude;


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