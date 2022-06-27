package com.gdng.entity.order.dao;

import com.gdng.entity.order.po.OrderPO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 订单表 Mapper 接口
 * </p>
 *
 * @author gc
 * @since 2022-06-26
 */
@Mapper
public interface OrderDao extends BaseMapper<OrderPO> {

}
