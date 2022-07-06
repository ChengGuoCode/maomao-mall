package com.gdng.inner.api.goods.fallback;

import com.gdng.inner.api.goods.dto.CategoryResDTO;
import com.gdng.inner.api.goods.invoke.CategoryRemote;
import com.gdng.support.common.dto.res.ResDTO;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Auther: guocheng
 * @CreateDate: 2022/7/5 15:15
 * @Description:
 * @Version: 1.0.0
 */
@Component
public class CategoryRemoteFallbackFactory implements FallbackFactory<CategoryRemote> {
    @Override
    public CategoryRemote create(Throwable cause) {
        return new CategoryRemote() {
            @Override
            public ResDTO<List<CategoryResDTO>> getTab() {
                return ResDTO.buildBusyResult();
            }
        };
    }
}
