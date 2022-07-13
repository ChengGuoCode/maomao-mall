package com.gdng.core.merchant.service;

import com.gdng.inner.api.merchant.dto.StoreDTO;

import java.util.List;

public interface StoreService {

    List<StoreDTO> getStoreByIds(List<Long> storeIds);

}
