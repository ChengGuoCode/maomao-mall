package com.gdng.entity.user.dao;

import com.gdng.entity.user.po.PermissionPO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 权限表 Mapper 接口
 * </p>
 *
 * @author gc
 * @since 2022-06-26
 */
@Mapper
public interface PermissionDao extends BaseMapper<PermissionPO> {

}
