package com.gdng.support.common.cache.redis.goods;

import com.gdng.support.common.cache.redis.RedisCache;
import com.gdng.support.common.cache.redis.goods.dto.StoreProductDTO;
import com.gdng.support.common.cache.redis.goods.dto.StoreProductSkuDTO;
import com.gdng.support.common.cache.util.CacheUtil;
import com.gdng.support.common.util.JacksonUtil;

import java.util.Map;

/**
 * @Auther: guocheng
 * @CreateDate: 2022/7/8 10:26
 * @Description:
 * @Version: 1.0.0
 */
public class GoodsRedisCache {

    private static final RedisCache STORE_PRODUCT_CACHE = new RedisCache("goods:store:product:");
    private static final RedisCache STORE_PRODUCT_SKU_CACHE = new RedisCache("goods:store:product:sku:");

    public static StoreProductDTO getStoreProduct(Long businessId, Long storeId, Long productId) {
        Object storeProduct = STORE_PRODUCT_CACHE.hget("key", CacheUtil.getHashKey(businessId, storeId, productId));
        return storeProduct == null ? null : JacksonUtil.jsonToBean(storeProduct.toString(), StoreProductDTO.class);
    }

    public static void setStoreProduct(StoreProductDTO storeProduct) {
        STORE_PRODUCT_CACHE.hset("key", CacheUtil.getHashKey(storeProduct.getBusinessId(), storeProduct.getStoreId(),
                        storeProduct.getProductId()), JacksonUtil.anyToJson(storeProduct));
    }

    public static void multiSetStoreProduct(Map<String, String> storeProductMap) {
        STORE_PRODUCT_CACHE.multiHset("key", storeProductMap);
    }

    public static StoreProductSkuDTO getStoreProductSku(Long businessId, Long storeId, Long productId, String skuCode) {
        Object storeProductsku = STORE_PRODUCT_SKU_CACHE.hget("key", CacheUtil.getHashKey(businessId, storeId, productId, skuCode));
        return storeProductsku == null ? null : JacksonUtil.jsonToBean(storeProductsku.toString(), StoreProductSkuDTO.class);
    }

    public static void setStoreProductSku(StoreProductSkuDTO storeProductsku) {
        STORE_PRODUCT_SKU_CACHE.hset("key", CacheUtil.getHashKey(storeProductsku.getBusinessId(),
                storeProductsku.getStoreId(), storeProductsku.getProductId(), storeProductsku.getSkuCode()),
                JacksonUtil.anyToJson(storeProductsku));
    }

    public static void multiSetStoreProductSku(Map<String, String> storeProductSkuMap) {
        STORE_PRODUCT_SKU_CACHE.multiHset("key", storeProductSkuMap);
    }

}
