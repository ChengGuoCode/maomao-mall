package com.gdng.entity.payment.dao;

import com.gdng.entity.payment.po.OrderRefundPO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 订单退款表 Mapper 接口
 * </p>
 *
 * @author gc
 * @since 2022-07-25
 */
@Mapper
public interface OrderRefundDao extends BaseMapper<OrderRefundPO> {

}
