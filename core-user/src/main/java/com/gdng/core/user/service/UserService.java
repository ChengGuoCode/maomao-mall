package com.gdng.core.user.service;

import com.gdng.support.common.dto.UserDTO;

public interface UserService {

    UserDTO login(UserDTO userDTO);

    UserDTO addOrUpdate(UserDTO userDTO);

    void syncRolePermissionCache();

}
