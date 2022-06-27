package com.gdng.entity.user.dao;

import com.gdng.entity.user.po.UserRolePO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户角色表 Mapper 接口
 * </p>
 *
 * @author gc
 * @since 2022-06-26
 */
@Mapper
public interface UserRoleDao extends BaseMapper<UserRolePO> {

}
