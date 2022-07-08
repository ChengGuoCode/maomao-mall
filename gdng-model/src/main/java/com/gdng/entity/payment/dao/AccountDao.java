package com.gdng.entity.payment.dao;

import com.gdng.entity.payment.po.AccountPO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 支付账户表 Mapper 接口
 * </p>
 *
 * @author gc
 * @since 2022-07-08
 */
@Mapper
public interface AccountDao extends BaseMapper<AccountPO> {

}
