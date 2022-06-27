package com.gdng.core.order.dao.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gdng.core.order.dao.service.OrderDaoService;
import com.gdng.entity.order.dao.OrderDao;
import com.gdng.entity.order.po.OrderPO;
import org.springframework.stereotype.Service;

@Service
public class OrderDaoServiceImpl extends ServiceImpl<OrderDao, OrderPO> implements OrderDaoService {
}
