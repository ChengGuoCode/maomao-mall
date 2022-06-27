package com.gdng.core.merchant.service.impl;

import com.gdng.core.merchant.dao.service.BusinessDaoService;
import com.gdng.core.merchant.service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BusinessServiceImpl implements BusinessService {

    private final BusinessDaoService businessDaoService;

    @Autowired
    public BusinessServiceImpl(BusinessDaoService businessDaoService) {
        this.businessDaoService = businessDaoService;
    }
}
