package com.gdng.core.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.gdng.core.user.dao.service.*;
import com.gdng.core.user.service.UserService;
import com.gdng.entity.user.po.RolePO;
import com.gdng.entity.user.po.UserPO;
import com.gdng.entity.user.po.UserRolePO;
import com.gdng.support.common.cache.redis.user.UserRedisCache;
import com.gdng.support.common.dto.UserDTO;
import com.gdng.support.common.dto.res.GlobalResponseEnum;
import com.gdng.support.common.exception.GdngException;
import com.gdng.support.common.security.SecurityStrategyUtil;
import com.gdng.support.common.security.asyCrypt.AsyCryptAlgEnum;
import com.gdng.support.common.security.asyCrypt.AsyCryptUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Value("${auth.token.expireTime}")
    private Long expireTime;

    private final UserDaoService userDaoService;
    private final RoleDaoService roleDaoService;
    private final PermissionDaoService permissionDaoService;
    private final UserRoleDaoService userRoleDaoService;
    private final RolePermissionDaoService rolePermissionDaoService;

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    public UserServiceImpl(UserDaoService userDaoService, RoleDaoService roleDaoService, PermissionDaoService permissionDaoService, UserRoleDaoService userRoleDaoService, RolePermissionDaoService rolePermissionDaoService) {
        this.userDaoService = userDaoService;
        this.roleDaoService = roleDaoService;
        this.permissionDaoService = permissionDaoService;
        this.userRoleDaoService = userRoleDaoService;
        this.rolePermissionDaoService = rolePermissionDaoService;
    }

    @Override
    public UserDTO login(UserDTO userDTO) {
        String username = userDTO.getUsername();
        String password = userDTO.getPassword();
        if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
            throw new GdngException(GlobalResponseEnum.BIZ_PARAM_ERR, "用户名密码不能为空");
        }

        UserPO userPO = userDaoService.getOne(new QueryWrapper<UserPO>().eq("username", username));

        if (userPO == null) {
            throw new GdngException(GlobalResponseEnum.USER_NONEXIST);
        }
        if (!passwordEncoder.matches(password, userPO.getPassword())) {
            throw new GdngException(GlobalResponseEnum.PASSWORD_ERR);
        }

        String uid = userPO.getId();
        List<GrantedAuthority> authorities = getAuthorities(uid);
        userDTO.setId(uid);
        userDTO.setPassword(null);
        userDTO.setAuthorities(authorities);
        AsyCryptAlgEnum alg = AsyCryptAlgEnum.RSA;
        String keyPair = UserRedisCache.getRSAKeyPair(alg.getAlgorithm());
        String publicKeyStr;
        if (keyPair == null || keyPair.split("#").length != 2) {
            Map<String, String> keyMap = AsyCryptUtil.createKeys(AsyCryptAlgEnum.RSA);
            Map.Entry<String, String> newKey = keyMap.entrySet().iterator().next();
            publicKeyStr = newKey.getKey();
            String privateKey = newKey.getValue();
            UserRedisCache.setRSAKeyPair(alg.getAlgorithm(), publicKeyStr + "#" + privateKey);
        } else {
            publicKeyStr = keyPair.split("#")[0];
        }
        userDTO.setToken(SecurityStrategyUtil.generateToken(alg, publicKeyStr, username, expireTime, uid));
        UserRedisCache.setUserInfo(userDTO);
        return userDTO;
    }

    @Override
    public void addOrUpdate(UserDTO userDTO) {
        String username = userDTO.getUsername();
        String password = userDTO.getPassword();
        if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
            throw new GdngException(GlobalResponseEnum.PARAM_ERR, "用户名或密码不能为空");
        }
        UserPO userPO = new UserPO();
        userPO.setUsername(username);
        userPO.setPassword(passwordEncoder.encode(password));
        userDaoService.saveOrUpdate(userPO);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserPO userPO = userDaoService.getOne(new QueryWrapper<UserPO>().eq("username", username));
        if (userPO == null) {
            throw new GdngException(GlobalResponseEnum.USER_NONEXIST);
        }
        String uid = userPO.getId();
        List<GrantedAuthority> authorities = getAuthorities(uid);
        return new User(username, userPO.getPassword(), authorities);
    }

    private List<GrantedAuthority> getAuthorities(String uid) {
        List<UserRolePO> userRolePOList = userRoleDaoService.list(new QueryWrapper<UserRolePO>().eq("uid", uid));
        List<GrantedAuthority> authorities = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(userRolePOList)) {
            List<Long> roleIdList = userRolePOList.stream().map(UserRolePO::getRid).collect(Collectors.toList());
            List<RolePO> rolePOList = roleDaoService.listByIds(roleIdList);
            if (CollectionUtils.isNotEmpty(rolePOList)) {
                authorities.addAll(rolePOList.stream().map(role -> new SimpleGrantedAuthority(role.getRoleName())).collect(Collectors.toList()));
            }
        }
        return authorities;
    }
}
