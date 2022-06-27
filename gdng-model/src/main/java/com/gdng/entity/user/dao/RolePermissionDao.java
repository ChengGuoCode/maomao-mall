package com.gdng.entity.user.dao;

import com.gdng.entity.user.po.RolePermissionPO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 角色权限表 Mapper 接口
 * </p>
 *
 * @author gc
 * @since 2022-06-26
 */
@Mapper
public interface RolePermissionDao extends BaseMapper<RolePermissionPO> {

}
