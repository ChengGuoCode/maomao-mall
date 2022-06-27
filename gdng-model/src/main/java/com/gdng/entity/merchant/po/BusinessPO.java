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
 * 商家表
 * </p>
 *
 * @author gc
 * @since 2022-06-26
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@TableName("mao_business")
@ApiModel(value="BusinessPO对象", description="商家表")
public class BusinessPO implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "商家ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;


    @ApiModelProperty(value = "商家编码")
    @TableField("business_code")
    private String businessCode;


    @ApiModelProperty(value = "商家名称")
    @TableField("name")
    private String name;


    @ApiModelProperty(value = "商家电话")
    @TableField("mobile")
    private String mobile;


    @ApiModelProperty(value = "商家地址")
    @TableField("address")
    private String address;


    @ApiModelProperty(value = "商家描述")
    @TableField("business_desc")
    private String businessDesc;


    @ApiModelProperty(value = "商家LOGO")
    @TableField("logo")
    private String logo;


    @ApiModelProperty(value = "商家邮箱")
    @TableField("email")
    private String email;


    @ApiModelProperty(value = "商家状态 0-待激活，1-正常，2-禁用")
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