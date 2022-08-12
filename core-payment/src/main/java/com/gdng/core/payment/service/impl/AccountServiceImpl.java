package com.gdng.core.payment.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gdng.core.payment.constant.AccountStatusEnum;
import com.gdng.core.payment.dao.service.AccountDaoService;
import com.gdng.core.payment.service.AccountService;
import com.gdng.entity.payment.po.AccountPO;
import com.gdng.inner.api.payment.constant.AccountTypeEnum;
import com.gdng.inner.api.payment.dto.AccountDTO;
import com.gdng.support.common.dto.res.GlobalResponseEnum;
import com.gdng.support.common.exception.GdngException;
import com.gdng.support.common.util.GdngBeanUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Auther: guocheng
 * @CreateDate: 2022/7/19 11:18
 * @Description:
 * @Version: 1.0.0
 */
@Service
public class AccountServiceImpl implements AccountService {

    private final AccountDaoService accDaoService;

    @Autowired
    public AccountServiceImpl(AccountDaoService accDaoService) {
        this.accDaoService = accDaoService;
    }

    @Override
    public void addOrUpdate(AccountDTO accDTO) {
        checkAccParam(accDTO);
        Long accId = accDTO.getId();
        if (accId != null) {
            AccountPO accountPO = accDaoService.getById(accId);
            String payPass = accDTO.getPayPass();
            boolean isUpdate = false;
            if (payPass != null && !payPass.equals(accountPO.getPayPass())) {
                accountPO.setPayPass(payPass);
                isUpdate = true;
            }
            Integer accStatus = accDTO.getAccStatus();
            if (accStatus != null && !accStatus.equals(accountPO.getAccStatus())) {
                accountPO.setAccStatus(accStatus);
                isUpdate = true;
            }
            if (isUpdate) {
                accDaoService.updateById(accountPO);
            }
        } else {
            AccountPO accountPO = GdngBeanUtil.copyToNewBean(accDTO, AccountPO.class);
            accDaoService.save(accountPO);
        }
    }

    @Override
    public Long getAccBalance(String uid) {
        AccountPO account = accDaoService.getOne(new QueryWrapper<AccountPO>().eq("type", AccountTypeEnum.INDIVIDUAL)
                .eq("corelation_id", uid));
        return account.getBalance();
    }

    private void checkAccParam(AccountDTO accDTO) {
        Integer type = accDTO.getType();
        if (type == null || AccountTypeEnum.getAccByType(type) == null) {
            throw new GdngException(GlobalResponseEnum.BIZ_PARAM_ERR, "无效的账户类型");
        }

        String corelationId = accDTO.getCorelationId();
        if (StringUtils.isBlank(corelationId)) {
            throw new GdngException(GlobalResponseEnum.BIZ_PARAM_ERR, "无效的账户关联用户");
        }

        String payPass = accDTO.getPayPass();
        if (payPass != null && payPass.length() != 6) {
            throw new GdngException(GlobalResponseEnum.BIZ_PARAM_ERR, "请设置6位支付密码");
        }

        Integer accStatus = accDTO.getAccStatus();
        if (accStatus != null && AccountStatusEnum.getAccStatus(accStatus) == null) {
            throw new GdngException(GlobalResponseEnum.BIZ_PARAM_ERR, "无效的账户状态");
        }
    }
}
