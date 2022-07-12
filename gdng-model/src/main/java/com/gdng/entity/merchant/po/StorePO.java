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
 * 店铺表
 * </p>
 *
 * @author gc
 * @since 2022-06-26
 */
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
    @TableField("beneficiary")
    private Long beneficiary;


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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStoreCode() {
        return storeCode;
    }

    public void setStoreCode(String storeCode) {
        this.storeCode = storeCode;
    }

    public Long getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Long businessId) {
        this.businessId = businessId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStoreType() {
        return storeType;
    }

    public void setStoreType(Integer storeType) {
        this.storeType = storeType;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStoreDesc() {
        return storeDesc;
    }

    public void setStoreDesc(String storeDesc) {
        this.storeDesc = storeDesc;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getBackImg() {
        return backImg;
    }

    public void setBackImg(String backImg) {
        this.backImg = backImg;
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

    public Long getBeneficiary() {
        return beneficiary;
    }

    public void setBeneficiary(Long beneficiary) {
        this.beneficiary = beneficiary;
    }

    public Integer getDelivery() {
        return delivery;
    }

    public void setDelivery(Integer delivery) {
        this.delivery = delivery;
    }

    public Long getFavoriteNum() {
        return favoriteNum;
    }

    public void setFavoriteNum(Long favoriteNum) {
        this.favoriteNum = favoriteNum;
    }

    public String getOpenStartTime() {
        return openStartTime;
    }

    public void setOpenStartTime(String openStartTime) {
        this.openStartTime = openStartTime;
    }

    public String getOpenEndTime() {
        return openEndTime;
    }

    public void setOpenEndTime(String openEndTime) {
        this.openEndTime = openEndTime;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
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