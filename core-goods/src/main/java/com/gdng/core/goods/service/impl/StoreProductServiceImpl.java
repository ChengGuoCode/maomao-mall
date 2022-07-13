package com.gdng.core.goods.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gdng.core.goods.dao.service.CarouselDaoService;
import com.gdng.core.goods.dao.service.StoreProductDaoService;
import com.gdng.core.goods.dao.service.StoreProductSkuDaoService;
import com.gdng.core.goods.service.StoreProductService;
import com.gdng.entity.goods.po.CarouselPO;
import com.gdng.entity.goods.po.StoreProductPO;
import com.gdng.entity.goods.po.StoreProductSkuPO;
import com.gdng.inner.api.goods.dto.CarouselResDTO;
import com.gdng.inner.api.goods.dto.StoreProductReqDTO;
import com.gdng.inner.api.goods.dto.StoreProductResDTO;
import com.gdng.inner.api.goods.dto.StoreProductSkuResDTO;
import com.gdng.support.common.dto.res.PageResDTO;
import com.gdng.support.common.util.GdngBeanUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class StoreProductServiceImpl implements StoreProductService {

    private final StoreProductDaoService storeProductDaoService;
    private final StoreProductSkuDaoService storeProductSkuDaoService;
    private final CarouselDaoService carouselDaoService;

    @Autowired
    public StoreProductServiceImpl(StoreProductDaoService storeProductDaoService, StoreProductSkuDaoService storeProductSkuDaoService, CarouselDaoService carouselDaoService) {
        this.storeProductDaoService = storeProductDaoService;
        this.storeProductSkuDaoService = storeProductSkuDaoService;
        this.carouselDaoService = carouselDaoService;
    }

    @Override
    public List<CarouselResDTO> getCarousel() {
        List<CarouselPO> carouselPOList = carouselDaoService.list();
        List<CarouselResDTO> carouselDTOList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(carouselPOList)) {
            AtomicInteger noAtomic = new AtomicInteger(0);
            carouselDTOList.addAll(carouselPOList.stream().map(carouselPO -> {
                CarouselResDTO carouselResDTO = GdngBeanUtil.copyToNewBean(carouselPO, CarouselResDTO.class);
                carouselResDTO.setNo(noAtomic.incrementAndGet());
                return carouselResDTO;
            }).collect(Collectors.toList()));
        }
        return carouselDTOList;
    }

    @SuppressWarnings("unchecked")
    @Override
    public PageResDTO<StoreProductResDTO> getGoodsList(StoreProductReqDTO reqDTO) {
        long pageNo = reqDTO.getPageNo();
        long pageSize = reqDTO.getPageSize();
        Page<StoreProductPO> page = new Page<>(pageNo, pageSize);

        QueryWrapper<StoreProductPO> queryWrapper = new QueryWrapper<>();

        String productName = reqDTO.getProductName();
        if (StringUtils.isNotBlank(productName)) {
            queryWrapper.like("alias", productName);
        }

        Long categoryId = reqDTO.getCategoryId();
        if (categoryId != null) {
            queryWrapper.eq("category_id", categoryId);
        }

        page = storeProductDaoService.page(page, queryWrapper);
        PageResDTO<StoreProductResDTO> pageResDTO = PageResDTO.buildEmptyPage(page);
        if (page.getTotal() != 0) {
            List<StoreProductPO> records = page.getRecords();
            List<Long> storeProductIdList = records.stream().map(StoreProductPO::getId).collect(Collectors.toList());
            List<StoreProductSkuPO> storeProductSkuPOList =
                    storeProductSkuDaoService.list(new QueryWrapper<StoreProductSkuPO>().in("store_product_id", storeProductIdList));
            Map<Long, List<StoreProductSkuPO>> storeProductSkuMap = new HashMap<>();
            if (CollectionUtils.isNotEmpty(storeProductSkuPOList)) {
                storeProductSkuMap.putAll(storeProductSkuPOList.stream().collect(Collectors.groupingBy(StoreProductSkuPO::getStoreProductId)));
            }
            pageResDTO.setRecords(records.stream().map(storeProductPO -> {
                StoreProductResDTO storeProductResDTO = GdngBeanUtil.copyToNewBean(storeProductPO, StoreProductResDTO.class);
                List<StoreProductSkuPO> storeProductSkuPOS = storeProductSkuMap.get(storeProductPO.getId());
                if (CollectionUtils.isNotEmpty(storeProductSkuPOS)) {
                    Long minPriceSkuId = storeProductSkuPOS.get(0).getId();
                    if (storeProductSkuPOS.size() > 1) {
                        StoreProductSkuPO minPriceSku = storeProductSkuPOS.stream().min(Comparator.comparing(StoreProductSkuPO::getPrice)).get();
                        minPriceSkuId = minPriceSku.getId();
                    }
                    Long finalMinPriceSkuId = minPriceSkuId;
                    storeProductResDTO.setSkuList(storeProductSkuPOS.stream().map(storeProductSkuPO -> {
                        StoreProductSkuResDTO storeProductSkuResDTO = GdngBeanUtil.copyToNewBean(storeProductSkuPO, StoreProductSkuResDTO.class);
                        if (Objects.equals(storeProductSkuPO.getId(), finalMinPriceSkuId)) {
                            storeProductSkuResDTO.setSelected(true);
                        }
                        return storeProductSkuResDTO;
                    }).collect(Collectors.toList()));
                }
                return storeProductResDTO;
            }).collect(Collectors.toList()));
        }
        return pageResDTO;
    }
}
