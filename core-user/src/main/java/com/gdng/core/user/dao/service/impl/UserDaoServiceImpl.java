package com.gdng.core.user.dao.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gdng.core.user.dao.service.UserDaoService;
import com.gdng.entity.user.dao.UserDao;
import com.gdng.entity.user.po.UserPO;
import org.springframework.stereotype.Service;

@Service
public class UserDaoServiceImpl extends ServiceImpl<UserDao, UserPO> implements UserDaoService {
}
