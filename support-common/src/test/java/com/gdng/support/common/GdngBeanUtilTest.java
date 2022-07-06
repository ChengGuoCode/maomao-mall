package com.gdng.support.common;

import com.gdng.support.common.dto.req.PageReqDTO;
import com.gdng.support.common.dto.res.PageResDTO;
import com.gdng.support.common.util.GdngBeanUtil;
import com.gdng.support.common.util.JacksonUtil;
import org.junit.Test;

/**
 * @Auther: guocheng
 * @CreateDate: 2022/7/6 14:09
 * @Description:
 * @Version: 1.0.0
 */
public class GdngBeanUtilTest {

    @Test
    public void testCopyToNewBean() {
        PageReqDTO pageReqDTO = new PageReqDTO();
        pageReqDTO.setPageNo(2);
        pageReqDTO.setPageSize(50);
        PageResDTO pageResDTO = GdngBeanUtil.copyToNewBean(pageReqDTO, PageResDTO.class);
        System.out.println(JacksonUtil.anyToJson(pageResDTO));
    }

}
