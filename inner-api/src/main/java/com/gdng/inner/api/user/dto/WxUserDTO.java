package com.gdng.inner.api.user.dto;

import com.gdng.support.common.dto.UserDTO;

/**
 * @Auther: guocheng
 * @CreateDate: 2022/8/2 10:23
 * @Description:
 * @Version: 1.0.0
 */
public class WxUserDTO extends UserDTO {

    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
