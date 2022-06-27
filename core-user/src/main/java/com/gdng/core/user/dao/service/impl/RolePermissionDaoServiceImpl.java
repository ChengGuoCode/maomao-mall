package com.gdng.core.user.dao.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gdng.core.user.dao.service.RolePermissionDaoService;
import com.gdng.entity.user.dao.RolePermissionDao;
import com.gdng.entity.user.po.RolePermissionPO;
import org.springframework.stereotype.Service;

@Service
public class RolePermissionDaoServiceImpl extends ServiceImpl<RolePermissionDao, RolePermissionPO> implements RolePermissionDaoService {
}
