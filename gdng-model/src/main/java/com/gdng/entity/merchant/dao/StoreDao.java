package com.gdng.entity.merchant.dao;

import com.gdng.entity.merchant.po.StorePO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 店铺表 Mapper 接口
 * </p>
 *
 * @author gc
 * @since 2022-06-26
 */
@Mapper
public interface StoreDao extends BaseMapper<StorePO> {

}
