package com.gdng.core.merchant.service.impl;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.gdng.core.merchant.dao.service.StoreDaoService;
import com.gdng.core.merchant.service.StoreService;
import com.gdng.entity.merchant.po.StorePO;
import com.gdng.inner.api.merchant.dto.StoreDTO;
import com.gdng.support.common.util.GdngBeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StoreServiceImpl implements StoreService {

    private final StoreDaoService storeDaoService;

    @Autowired
    public StoreServiceImpl(StoreDaoService storeDaoService) {
        this.storeDaoService = storeDaoService;
    }

    @Override
    public List<StoreDTO> getStoreByIds(List<Long> storeIds) {
        List<StorePO> storePOList = storeDaoService.listByIds(storeIds);
        List<StoreDTO> storeDTOList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(storePOList)) {
            storeDTOList.addAll(storePOList.stream().map(storePO -> GdngBeanUtil.copyToNewBean(storePO, StoreDTO.class)).collect(Collectors.toList()));
        }
        return storeDTOList;
    }
}
