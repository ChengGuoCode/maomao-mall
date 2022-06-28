package com.gdng.entity.goods.po;

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
 * 店铺商品表
 * </p>
 *
 * @author gc
 * @since 2022-06-26
 */
@TableName("mao_store_product")
@ApiModel(value="StoreProductPO对象", description="店铺商品表")
public class StoreProductPO implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "店铺商品ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;


    @ApiModelProperty(value = "商家ID")
    @TableField("business_id")
    private Long businessId;


    @ApiModelProperty(value = "店铺ID")
    @TableField("store_id")
    private Long storeId;


    @ApiModelProperty(value = "商品ID")
    @TableField("product_id")
    private Long productId;


    @ApiModelProperty(value = "商品编码")
    @TableField("product_code")
    private String productCode;


    @ApiModelProperty(value = "类目ID")
    @TableField("category_id")
    private Long categoryId;


    @ApiModelProperty(value = "商品类型")
    @TableField("product_type")
    private Integer productType;


    @ApiModelProperty(value = "品牌ID")
    @TableField("brand_id")
    private Long brandId;


    @ApiModelProperty(value = "商品别名")
    @TableField("alias")
    private String alias;


    @ApiModelProperty(value = "店铺商品价格")
    @TableField("price")
    private Long price;


    @ApiModelProperty(value = "商品主图")
    @TableField("pic")
    private String pic;


    @ApiModelProperty(value = "商品主标签")
    @TableField("label")
    private String label;


    @ApiModelProperty(value = "商品描述")
    @TableField("product_desc")
    private String productDesc;


    @ApiModelProperty(value = "使用说明")
    @TableField("use_explain")
    private String useExplain;


    @ApiModelProperty(value = "库存")
    @TableField("stock")
    private Integer stock;


    @ApiModelProperty(value = "店铺商品权重")
    @TableField("weight")
    private Integer weight;


    @ApiModelProperty(value = "商品销售状态 0-销售中，1-售罄，2-下架，3-停用")
    @TableField("sale_status")
    private Integer saleStatus;


    @ApiModelProperty(value = "销量")
    @TableField("sale_volume")
    private Long saleVolume;


    @ApiModelProperty(value = "热度")
    @TableField("hot")
    private Long hot;


    @ApiModelProperty(value = "退货政策 0-不允许退货，1-七天无理由退货，2-七天有理由退货")
    @TableField("return_policy")
    private Integer returnPolicy;


    @ApiModelProperty(value = "换货政策 0-不允许换货，1-七天无理由换货，2-七天有理由换货")
    @TableField("exchange_policy")
    private Integer exchangePolicy;


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

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getProductType() {
        return productType;
    }

    public void setProductType(Integer productType) {
        this.productType = productType;
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public String getUseExplain() {
        return useExplain;
    }

    public void setUseExplain(String useExplain) {
        this.useExplain = useExplain;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getSaleStatus() {
        return saleStatus;
    }

    public void setSaleStatus(Integer saleStatus) {
        this.saleStatus = saleStatus;
    }

    public Long getSaleVolume() {
        return saleVolume;
    }

    public void setSaleVolume(Long saleVolume) {
        this.saleVolume = saleVolume;
    }

    public Long getHot() {
        return hot;
    }

    public void setHot(Long hot) {
        this.hot = hot;
    }

    public Integer getReturnPolicy() {
        return returnPolicy;
    }

    public void setReturnPolicy(Integer returnPolicy) {
        this.returnPolicy = returnPolicy;
    }

    public Integer getExchangePolicy() {
        return exchangePolicy;
    }

    public void setExchangePolicy(Integer exchangePolicy) {
        this.exchangePolicy = exchangePolicy;
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