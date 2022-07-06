package com.gdng.support.common.util;

import org.springframework.beans.BeanUtils;

/**
 * @Auther: guocheng
 * @CreateDate: 2022/7/6 14:03
 * @Description:
 * @Version: 1.0.0
 */
public class GdngBeanUtil {

    public static <T> T copyToNewBean(Object source, Class<T> valueType) {
        T target = BeanUtils.instantiateClass(valueType);
        BeanUtils.copyProperties(source, target);
        return target;
    }

}
