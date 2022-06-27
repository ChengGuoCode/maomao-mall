package com.gdng.support.common.dto;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

@Data
public class UserDTO {

    private String id;
    private String username;
    private String password;
    private String token;
    private List<GrantedAuthority> authorities;

}
