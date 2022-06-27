package com.gdng.entity.goods.dao;

import com.gdng.entity.goods.po.StoreProductPO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 店铺商品表 Mapper 接口
 * </p>
 *
 * @author gc
 * @since 2022-06-26
 */
@Mapper
public interface StoreProductDao extends BaseMapper<StoreProductPO> {

}
