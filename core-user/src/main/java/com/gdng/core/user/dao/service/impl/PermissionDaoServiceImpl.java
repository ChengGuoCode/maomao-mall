package com.gdng.core.user.dao.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gdng.core.user.dao.service.PermissionDaoService;
import com.gdng.entity.user.dao.PermissionDao;
import com.gdng.entity.user.po.PermissionPO;
import org.springframework.stereotype.Service;

@Service
public class PermissionDaoServiceImpl extends ServiceImpl<PermissionDao, PermissionPO> implements PermissionDaoService {
}
