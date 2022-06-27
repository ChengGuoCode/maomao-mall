package com.gdng.entity.goods.dao;

import com.gdng.entity.goods.po.ProductPO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 商品表 Mapper 接口
 * </p>
 *
 * @author gc
 * @since 2022-06-26
 */
@Mapper
public interface ProductDao extends BaseMapper<ProductPO> {

}
