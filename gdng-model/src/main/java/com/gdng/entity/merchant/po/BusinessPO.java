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

/**
 * <p>
 * 商家表
 * </p>
 *
 * @author gc
 * @since 2022-06-26
 */
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBusinessCode() {
        return businessCode;
    }

    public void setBusinessCode(String businessCode) {
        this.businessCode = businessCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBusinessDesc() {
        return businessDesc;
    }

    public void setBusinessDesc(String businessDesc) {
        this.businessDesc = businessDesc;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRefuseReason() {
        return refuseReason;
    }

    public void setRefuseReason(String refuseReason) {
        this.refuseReason = refuseReason;
    }

    public String getAuditor() {
        return auditor;
    }

    public void setAuditor(String auditor) {
        this.auditor = auditor;
    }

    public Date getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
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