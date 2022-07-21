package com.gdng.entity.task.po;

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
 * 任务奖品表
 * </p>
 *
 * @author gc
 * @since 2022-07-21
 */
@TableName("mao_task_prize")
@ApiModel(value="TaskPrizePO对象", description="任务奖品表")
public class TaskPrizePO implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "任务ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;


    @ApiModelProperty(value = "任务ID")
    @TableField("task_id")
    private Long taskId;


    @ApiModelProperty(value = "策略ID")
    @TableField("strategy_id")
    private Long strategyId;


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


    @ApiModelProperty(value = "商品主图")
    @TableField("pic")
    private String pic;


    @ApiModelProperty(value = "sku编码")
    @TableField("sku_code")
    private String skuCode;


    @ApiModelProperty(value = "赠送数量")
    @TableField("send_num")
    private Integer sendNum;


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

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public Long getStrategyId() {
        return strategyId;
    }

    public void setStrategyId(Long strategyId) {
        this.strategyId = strategyId;
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

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode;
    }

    public Integer getSendNum() {
        return sendNum;
    }

    public void setSendNum(Integer sendNum) {
        this.sendNum = sendNum;
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