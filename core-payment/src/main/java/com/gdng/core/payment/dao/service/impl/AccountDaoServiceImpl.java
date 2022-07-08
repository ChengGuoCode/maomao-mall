package com.gdng.core.payment.dao.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gdng.core.payment.dao.service.AccountDaoService;
import com.gdng.entity.payment.dao.AccountDao;
import com.gdng.entity.payment.po.AccountPO;
import org.springframework.stereotype.Service;

@Service
public class AccountDaoServiceImpl extends ServiceImpl<AccountDao, AccountPO> implements AccountDaoService {

}
