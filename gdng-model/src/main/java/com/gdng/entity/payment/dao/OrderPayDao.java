package com.gdng.entity.payment.dao;

import com.gdng.entity.payment.po.OrderPayPO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 订单支付表 Mapper 接口
 * </p>
 *
 * @author gc
 * @since 2022-07-08
 */
@Mapper
public interface OrderPayDao extends BaseMapper<OrderPayPO> {

}
