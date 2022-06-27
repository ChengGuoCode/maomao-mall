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
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 店铺商品规格表
 * </p>
 *
 * @author gc
 * @since 2022-06-26
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@TableName("mao_store_product_sku")
@ApiModel(value="StoreProductSkuPO对象", description="店铺商品规格表")
public class StoreProductSkuPO implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "店铺skuID")
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


    @ApiModelProperty(value = "skuID")
    @TableField("sku_id")
    private Long skuId;


    @ApiModelProperty(value = "sku编码")
    @TableField("sku_code")
    private String skuCode;


    @ApiModelProperty(value = "店铺商品ID")
    @TableField("store_product_id")
    private Long storeProductId;


    @ApiModelProperty(value = "商品sku销售状态 0-销售中，1-售罄，2-下架，3-停用")
    @TableField("sale_status")
    private Integer saleStatus;


    @ApiModelProperty(value = "库存")
    @TableField("stock")
    private Integer stock;


    @ApiModelProperty(value = "锁定库存")
    @TableField("lock_stock")
    private Integer lockStock;


    @ApiModelProperty(value = "店铺商品sku价格")
    @TableField("price")
    private Long price;


    @ApiModelProperty(value = "规格值")
    @TableField("specification")
    private String specification;


    @ApiModelProperty(value = "销量")
    @TableField("sale_volume")
    private Long saleVolume;


    @ApiModelProperty(value = "热度")
    @TableField("hot")
    private Long hot;


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