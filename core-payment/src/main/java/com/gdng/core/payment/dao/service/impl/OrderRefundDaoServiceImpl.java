package com.gdng.core.payment.dao.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gdng.core.payment.dao.service.OrderRefundDaoService;
import com.gdng.entity.payment.dao.OrderRefundDao;
import com.gdng.entity.payment.po.OrderRefundPO;
import org.springframework.stereotype.Service;

@Service
public class OrderRefundDaoServiceImpl extends ServiceImpl<OrderRefundDao, OrderRefundPO> implements OrderRefundDaoService {

}
