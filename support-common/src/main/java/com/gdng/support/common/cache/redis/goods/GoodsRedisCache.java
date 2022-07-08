package com.gdng.support.common.cache.redis.goods;

import com.gdng.support.common.cache.redis.RedisCache;
import com.gdng.support.common.cache.redis.goods.dto.StoreProductDTO;
import com.gdng.support.common.cache.util.CacheUtil;
import com.gdng.support.common.util.JacksonUtil;

/**
 * @Auther: guocheng
 * @CreateDate: 2022/7/8 10:26
 * @Description:
 * @Version: 1.0.0
 */
public class GoodsRedisCache {

    private static final RedisCache STORE_PRODUCT_CACHE = new RedisCache("goods:store:product:");

    public static StoreProductDTO getStoreProduct(Long businessId, Long storeId, Long productId) {
        Object storeProduct = STORE_PRODUCT_CACHE.hget("key", CacheUtil.getHashKey(businessId, storeId, productId));
        return storeProduct == null ? null : JacksonUtil.jsonToBean(storeProduct.toString(), StoreProductDTO.class);
    }

    public static void setStoreProduct(StoreProductDTO storeProduct) {
        STORE_PRODUCT_CACHE.hset("key", CacheUtil.getHashKey(storeProduct.getBusinessId(), storeProduct.getStoreId(),
                        storeProduct.getProductId()), JacksonUtil.anyToJson(storeProduct));
    }

}
