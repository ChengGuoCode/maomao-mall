package com.gdng.core.user.dao.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gdng.core.user.dao.service.UserRoleDaoService;
import com.gdng.entity.user.dao.UserRoleDao;
import com.gdng.entity.user.po.UserRolePO;
import org.springframework.stereotype.Service;

@Service
public class UserRoleDaoServiceImpl extends ServiceImpl<UserRoleDao, UserRolePO> implements UserRoleDaoService {
}
