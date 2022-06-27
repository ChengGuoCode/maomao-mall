package com.gdng.core.merchant.service.impl;

import com.gdng.core.merchant.dao.service.StoreDaoService;
import com.gdng.core.merchant.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StoreServiceImpl implements StoreService {

    private final StoreDaoService storeDaoService;

    @Autowired
    public StoreServiceImpl(StoreDaoService storeDaoService) {
        this.storeDaoService = storeDaoService;
    }
}
