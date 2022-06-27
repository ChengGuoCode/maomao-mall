package com.gdng.core.merchant.dao.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gdng.core.merchant.dao.service.BusinessDaoService;
import com.gdng.entity.merchant.dao.BusinessDao;
import com.gdng.entity.merchant.po.BusinessPO;
import org.springframework.stereotype.Service;

@Service
public class BusinessDaoServiceImpl extends ServiceImpl<BusinessDao, BusinessPO> implements BusinessDaoService {
}
