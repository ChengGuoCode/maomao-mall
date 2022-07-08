package com.gdng.core.payment.dao.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gdng.core.payment.dao.service.OrderPayDetailDaoService;
import com.gdng.entity.payment.dao.OrderPayDetailDao;
import com.gdng.entity.payment.po.OrderPayDetailPO;
import org.springframework.stereotype.Service;

@Service
public class OrderPayDetailDaoServiceImpl extends ServiceImpl<OrderPayDetailDao, OrderPayDetailPO> implements OrderPayDetailDaoService {

}
