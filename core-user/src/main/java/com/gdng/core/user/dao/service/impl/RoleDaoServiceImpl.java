package com.gdng.core.user.dao.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gdng.core.user.dao.service.RoleDaoService;
import com.gdng.entity.user.dao.RoleDao;
import com.gdng.entity.user.po.RolePO;
import org.springframework.stereotype.Service;

@Service
public class RoleDaoServiceImpl extends ServiceImpl<RoleDao, RolePO> implements RoleDaoService {
}
