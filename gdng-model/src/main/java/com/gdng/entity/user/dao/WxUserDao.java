package com.gdng.entity.user.dao;

import com.gdng.entity.user.po.WxUserPO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 微信用户绑定表 Mapper 接口
 * </p>
 *
 * @author gc
 * @since 2022-08-02
 */
@Mapper
public interface WxUserDao extends BaseMapper<WxUserPO> {

}
