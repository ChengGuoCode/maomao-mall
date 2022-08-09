package com.gdng.core.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gdng.core.user.dao.service.WxUserDaoService;
import com.gdng.core.user.service.UserService;
import com.gdng.core.user.service.WxUserService;
import com.gdng.entity.user.po.WxUserPO;
import com.gdng.inner.api.user.dto.WxUserDTO;
import com.gdng.support.common.dto.UserDTO;
import com.gdng.support.common.dto.res.GlobalResponseEnum;
import com.gdng.support.common.exception.GdngException;
import com.gdng.support.common.util.JacksonUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * @Auther: guocheng
 * @CreateDate: 2022/8/2 10:29
 * @Description:
 * @Version: 1.0.0
 */
@Service
public class WxUserServiceImpl implements WxUserService {

    @Value("${wx.login.url}")
    private String wxLoginUrl;

    private static final String OPENID = "openid";

    private final WxUserDaoService wxUserDaoService;
    private final UserService userService;
    private final RestTemplate restTemplate;

    @Autowired
    public WxUserServiceImpl(WxUserDaoService wxUserDaoService, UserService userService, RestTemplate restTemplate) {
        this.wxUserDaoService = wxUserDaoService;
        this.userService = userService;
        this.restTemplate = restTemplate;
    }

    @Override
    public UserDTO login(WxUserDTO userDTO) {
        String code = userDTO.getCode();
        if (StringUtils.isBlank(code)) {
            throw new GdngException(GlobalResponseEnum.PARAM_ERR, "微信登录编码不能为空");
        }
        String loginUrl = String.format(wxLoginUrl, code);
        ResponseEntity<String> resEntity = restTemplate.getForEntity(loginUrl, String.class);
        if (resEntity.getStatusCodeValue() != 200) {
            throw new GdngException(GlobalResponseEnum.REMOTE_ERR, "微信登录接口异常，请稍后重试");
        }
        String body = resEntity.getBody();
        Map<String, Object> loginDataMap = JacksonUtil.jsonToMap(body);
        String openid = String.valueOf(loginDataMap.get(OPENID));
        WxUserPO wxUser = wxUserDaoService.getOne(new QueryWrapper<WxUserPO>()
                .eq("openid", openid));
        UserDTO loginRes;
        userDTO.setCode(null);
        if (wxUser == null) {
            loginRes = userService.login(userDTO);
            String uid = loginRes.getId();
            WxUserPO wxUserPO = new WxUserPO();
            wxUserPO.setUid(uid);
            wxUserPO.setOpenid(openid);
            wxUserDaoService.save(wxUserPO);
        } else {
            String uid = wxUser.getUid();
            userDTO.setId(uid);
            loginRes = userService.login(userDTO);
        }
        return loginRes;
    }
}
