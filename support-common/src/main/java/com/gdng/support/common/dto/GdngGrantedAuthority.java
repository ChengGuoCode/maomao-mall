package com.gdng.support.common.dto;

/**
 * @Auther: guocheng
 * @CreateDate: 2022/7/15 13:45
 * @Description:
 * @Version: 1.0.0
 */
public class GdngGrantedAuthority {

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
