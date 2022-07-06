package com.gdng.inner.api.goods.invoke;

import com.gdng.inner.api.goods.dto.CategoryResDTO;
import com.gdng.inner.api.goods.fallback.CategoryRemoteFallbackFactory;
import com.gdng.support.common.dto.res.ResDTO;
import com.gdng.support.common.spring.feign.FeignConf;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @Auther: guocheng
 * @CreateDate: 2022/7/5 15:15
 * @Description:
 * @Version: 1.0.0
 */
@FeignClient(value="gdng-core-goods-procedure",path = "/core/goods/category",fallbackFactory=
        CategoryRemoteFallbackFactory.class,
        configuration = {FeignConf.class})
public interface CategoryRemote {

    @GetMapping("/getTab")
    ResDTO<List<CategoryResDTO>> getTab();

}
