package com.gdng.core.merchant.dao.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gdng.core.merchant.dao.service.StoreDaoService;
import com.gdng.entity.merchant.dao.StoreDao;
import com.gdng.entity.merchant.po.StorePO;
import org.springframework.stereotype.Service;

@Service
public class StoreDaoServiceImpl extends ServiceImpl<StoreDao, StorePO> implements StoreDaoService {
}
