package com.gdng.entity.merchant.dao;

import com.gdng.entity.merchant.po.BusinessPO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 商家表 Mapper 接口
 * </p>
 *
 * @author gc
 * @since 2022-06-26
 */
@Mapper
public interface BusinessDao extends BaseMapper<BusinessPO> {

}
