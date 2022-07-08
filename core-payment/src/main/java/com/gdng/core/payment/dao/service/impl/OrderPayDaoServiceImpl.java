package com.gdng.core.payment.dao.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gdng.core.payment.dao.service.OrderPayDaoService;
import com.gdng.entity.payment.dao.OrderPayDao;
import com.gdng.entity.payment.po.OrderPayPO;
import org.springframework.stereotype.Service;

@Service
public class OrderPayDaoServiceImpl extends ServiceImpl<OrderPayDao, OrderPayPO> implements OrderPayDaoService {

}
