package com.gdng.support.common.dto;

import java.io.Serializable;
import java.util.List;

public class UserDTO implements Serializable {

    private String id;
    private String username;
    private String password;
    private String token;
    private List<GdngGrantedAuthority> authorities;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<GdngGrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<GdngGrantedAuthority> authorities) {
        this.authorities = authorities;
    }
}
