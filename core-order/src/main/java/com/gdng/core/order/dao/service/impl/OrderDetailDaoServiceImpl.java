package com.gdng.core.order.dao.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gdng.core.order.dao.service.OrderDetailDaoService;
import com.gdng.entity.order.dao.OrderDetailDao;
import com.gdng.entity.order.po.OrderDetailPO;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailDaoServiceImpl extends ServiceImpl<OrderDetailDao, OrderDetailPO> implements OrderDetailDaoService {
}
