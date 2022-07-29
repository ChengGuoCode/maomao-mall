package com.gdng.support.common.dto;

import java.io.Serializable;

/**
 * @Auther: guocheng
 * @CreateDate: 2022/7/15 13:45
 * @Description:
 * @Version: 1.0.0
 */
public class GdngGrantedAuthority implements Serializable {

    private String roleName;

    public GdngGrantedAuthority() {
    }

    public GdngGrantedAuthority(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
