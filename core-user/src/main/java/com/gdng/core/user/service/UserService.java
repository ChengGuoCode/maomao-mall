package com.gdng.core.user.service;

import com.gdng.support.common.dto.UserDTO;

import java.util.Map;

public interface UserService {

    UserDTO login(UserDTO userDTO);

    UserDTO addOrUpdate(UserDTO userDTO);

    Map<String, String> getRolePermissionCache();

}
