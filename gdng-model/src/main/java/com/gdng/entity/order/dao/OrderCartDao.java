package com.gdng.entity.order.dao;

import com.gdng.entity.order.po.OrderCartPO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 订单购物车表 Mapper 接口
 * </p>
 *
 * @author gc
 * @since 2022-07-06
 */
@Mapper
public interface OrderCartDao extends BaseMapper<OrderCartPO> {

}
