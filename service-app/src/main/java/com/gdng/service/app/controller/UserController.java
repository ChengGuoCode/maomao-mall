package com.gdng.service.app.controller;

import com.gdng.inner.api.payment.constant.AccountTypeEnum;
import com.gdng.inner.api.payment.dto.AccountDTO;
import com.gdng.inner.api.payment.invoke.AccountRemote;
import com.gdng.inner.api.user.dto.WxUserDTO;
import com.gdng.inner.api.user.invoke.UserRemote;
import com.gdng.inner.api.user.invoke.WxUserRemote;
import com.gdng.service.app.dto.UserItemDTO;
import com.gdng.support.common.dto.UserDTO;
import com.gdng.support.common.dto.res.ResDTO;
import com.gdng.support.common.spring.SpringContextHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: guocheng
 * @CreateDate: 2022/7/5 10:03
 * @Description:
 * @Version: 1.0.0
 */
@RestController
@RequestMapping("/service/app/user")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    private final UserRemote userRemote;
    private final WxUserRemote wxUserRemote;
    private final AccountRemote accRemote;

    @Autowired
    public UserController(UserRemote userRemote, WxUserRemote wxUserRemote, AccountRemote accRemote) {
        this.userRemote = userRemote;
        this.wxUserRemote = wxUserRemote;
        this.accRemote = accRemote;
    }

    @PostMapping("/wxlogin")
    public ResDTO<?> wxlogin(@RequestBody WxUserDTO userDTO) {
        return wxUserRemote.login(userDTO);
    }

    @PostMapping("/login")
    public ResDTO<?> login(@RequestBody UserDTO userDTO) {
        return userRemote.login(userDTO);
    }

    @PostMapping("/register")
    public ResDTO<?> register(@RequestBody UserDTO userDTO) {
        ResDTO<UserDTO> userResDTO = userRemote.addOrUpdate(userDTO);
        if (userResDTO.isSuccess()) {
            UserDTO user = userResDTO.getData();
            AccountDTO accDTO = new AccountDTO();
            accDTO.setType(AccountTypeEnum.INDIVIDUAL.getType());
            accDTO.setCorelationId(user.getId());
            ResDTO<?> resDTO = accRemote.addOrUpdate(accDTO);
            if (!resDTO.isSuccess()) {
                log.error("用户注册创建支付账户失败：{}", user.getUsername());
            }
        }
        return userResDTO;
    }

    @PostMapping("/getUserItems")
    public ResDTO<List<UserItemDTO>> getUserItems() {
        UserDTO user = SpringContextHolder.getUser();
        ResDTO<Long> accBalance = accRemote.getAccBalance(user.getId());
        List<UserItemDTO> userItems = new ArrayList<>();
        if (accBalance.isSuccess()) {
            UserItemDTO userItemDTO = new UserItemDTO();
            userItemDTO.setNo(0);
            userItemDTO.setName("积分");
            userItemDTO.setNum(accBalance.getData());
            userItems.add(userItemDTO);
        }
        return ResDTO.buildSuccessResult(userItems);
    }

}
