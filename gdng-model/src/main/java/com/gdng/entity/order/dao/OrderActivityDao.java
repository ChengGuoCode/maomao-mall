package com.gdng.entity.order.dao;

import com.gdng.entity.order.po.OrderActivityPO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 订单关联活动表 Mapper 接口
 * </p>
 *
 * @author gc
 * @since 2022-07-26
 */
@Mapper
public interface OrderActivityDao extends BaseMapper<OrderActivityPO> {

}
