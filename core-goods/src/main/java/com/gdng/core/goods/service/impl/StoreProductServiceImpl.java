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
import com.gdng.inner.api.goods.dto.*;
import com.gdng.support.common.dto.res.GlobalResponseEnum;
import com.gdng.support.common.dto.res.PageResDTO;
import com.gdng.support.common.exception.GdngException;
import com.gdng.support.common.lock.RedisLock;
import com.gdng.support.common.util.GdngBeanUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static com.gdng.support.common.constant.StringConstant.POUND;

@Service
public class StoreProductServiceImpl implements StoreProductService {

    private static final Logger log = LoggerFactory.getLogger(StoreProductServiceImpl.class);

    private static final String STOCK_LOCK = "goods_stock";

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

    @Override
    public void lockStock(List<StoreProductSkuStockDTO> reqDTOs) {
        changeStock(reqDTOs, 1);
    }

    @Override
    public void releaseStock(List<StoreProductSkuStockDTO> reqDTOs) {
        changeStock(reqDTOs, 2);
    }

    @Override
    public void reduceStock(List<StoreProductSkuStockDTO> reqDTOs) {
        changeStock(reqDTOs, 3);
    }

    private void changeStock(List<StoreProductSkuStockDTO> reqDTOs, int stockType) {
        Map<String, StoreProductSkuStockDTO> skuMap = new HashMap<>();
        List<Long> businessIdList = new ArrayList<>();
        List<Long> storeIdList = new ArrayList<>();
        List<Long> productIdList = new ArrayList<>();
        List<String> skuCodeList = new ArrayList<>();
        reqDTOs.forEach(sku -> {
            Long businessId = sku.getBusinessId();
            Long storeId = sku.getStoreId();
            Long productId = sku.getProductId();
            String skuCode = sku.getSkuCode();
            skuMap.put(businessId + POUND + storeId + POUND + productId + POUND + skuCode, sku);
            businessIdList.add(businessId);
            storeIdList.add(storeId);
            productIdList.add(productId);
            skuCodeList.add(skuCode);
        });
        Set<String> lockKeys = new TreeSet<>(skuMap.keySet());
        List<RedisLock> stockLocks = getStockLocks(lockKeys);

        try {
            List<StoreProductSkuPO> storeProductSkuList = storeProductSkuDaoService.list(new QueryWrapper<StoreProductSkuPO>()
                    .in("business_id", businessIdList)
                    .in("store_id", storeIdList)
                    .in("product_id", productIdList)
                    .in("sku_code", skuCodeList));
            if (CollectionUtils.isNotEmpty(storeProductSkuList)) {
                List<StoreProductSkuPO> skuList = storeProductSkuList.stream().map(sku -> {
                    Long businessId = sku.getBusinessId();
                    Long storeId = sku.getStoreId();
                    Long productId = sku.getProductId();
                    String skuCode = sku.getSkuCode();
                    StoreProductSkuStockDTO stockDTO = skuMap.get(businessId + POUND + storeId + POUND + productId + POUND + skuCode);
                    if (stockDTO == null) {
                        return null;
                    }
                    Integer goodsNum = stockDTO.getGoodsNum();
                    Integer stock = sku.getStock();
                    Integer lockStock = sku.getLockStock();
                    Long saleVolume = sku.getSaleVolume();
                    switch (stockType) {
                        case 1:
                            if (stock - lockStock - saleVolume < goodsNum) {
                                throw new GdngException(GlobalResponseEnum.BIZ_PARAM_ERR, "商品库存不足");
                            }
                            sku.setLockStock(lockStock + goodsNum);
                            break;
                        case 2:
                            if (lockStock < goodsNum) {
                                throw new GdngException(GlobalResponseEnum.BIZ_PARAM_ERR, "商品锁定库存数量不足，请校验参数");
                            }
                            sku.setLockStock(lockStock - goodsNum);
                            break;
                        case 3:
                            Long payment = stockDTO.getPayment();
                            if (payment == 0L) {
                                if (stock - lockStock - saleVolume < goodsNum) {
                                    throw new GdngException(GlobalResponseEnum.BIZ_PARAM_ERR, "商品库存不足");
                                }
                            } else {
                                if (lockStock < goodsNum) {
                                    throw new GdngException(GlobalResponseEnum.BIZ_PARAM_ERR, "商品锁定库存数量不足，请校验参数");
                                }
                                sku.setLockStock(lockStock - goodsNum);
                            }
                            sku.setSaleVolume(saleVolume + goodsNum);
                            break;
                    }
                    return sku;
                }).filter(Objects::nonNull).collect(Collectors.toList());
                storeProductSkuDaoService.updateBatchById(skuList);
            }
        } finally {
            if (CollectionUtils.isNotEmpty(stockLocks)) {
                for (RedisLock stockLock : stockLocks) {
                    try {
                        stockLock.unlock();
                    } catch (Exception e) {
                        log.error("库存锁释放异常", e);
                    }
                }
            }
        }
    }

    private List<RedisLock> getStockLocks(Set<String> lockKeys) {
        List<RedisLock> lockList = new ArrayList<>();
        for (String lockKey : lockKeys) {
            RedisLock redisLock = new RedisLock(STOCK_LOCK);
            boolean success = redisLock.lock(lockKey);
            if (!success) {
                for (RedisLock alreadyLock : lockList) {
                    try {
                        alreadyLock.unlock();
                    } catch (Exception e) {
                        log.error("库存锁释放异常:{}", lockKey, e);
                    }
                }
                throw new GdngException(GlobalResponseEnum.BIZ_PARAM_ERR, "获取库存锁异常，请稍后重试");
            }
            lockList.add(redisLock);
        }
        return lockList;
    }
}
