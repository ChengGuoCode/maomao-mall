package com.gdng.core.goods.common;

import com.gdng.core.goods.service.StoreProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @Auther: guocheng
 * @CreateDate: 2022/8/11 11:01
 * @Description:
 * @Version: 1.0.0
 */
@Component
@DependsOn({"redisTemplate", "springContextHolder"})
public class GoodsCacheInitHandler {

    @Autowired
    private StoreProductService storeProductService;

    @PostConstruct
    public void initGoodsCache() {
        storeProductService.syncGoodsCache(null);
    }

}
