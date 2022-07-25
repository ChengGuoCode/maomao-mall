package com.gdng.entity.payment.dao;

import com.gdng.entity.payment.po.TaskPayPO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 任务支付表 Mapper 接口
 * </p>
 *
 * @author gc
 * @since 2022-07-25
 */
@Mapper
public interface TaskPayDao extends BaseMapper<TaskPayPO> {

}
