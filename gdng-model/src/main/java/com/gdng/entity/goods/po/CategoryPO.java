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
 * 类目表
 * </p>
 *
 * @author gc
 * @since 2022-06-26
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@TableName("mao_category")
@ApiModel(value="CategoryPO对象", description="类目表")
public class CategoryPO implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "类目ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;


    @ApiModelProperty(value = "类目编码")
    @TableField("category_code")
    private String categoryCode;


    @ApiModelProperty(value = "类目名称")
    @TableField("name")
    private String name;


    @ApiModelProperty(value = "类目状态 0-启用，1-禁用")
    @TableField("status")
    private Integer status;


    @ApiModelProperty(value = "类目图片")
    @TableField("pic")
    private String pic;


    @ApiModelProperty(value = "类目等级")
    @TableField("c_level")
    private Integer cLevel;


    @ApiModelProperty(value = "父类目ID")
    @TableField("parent_id")
    private Long parentId;


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