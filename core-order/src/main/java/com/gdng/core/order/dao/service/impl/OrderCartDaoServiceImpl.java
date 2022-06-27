package com.gdng.core.order.dao.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gdng.core.order.dao.service.OrderCartDaoService;
import com.gdng.entity.order.dao.OrderCartDao;
import com.gdng.entity.order.po.OrderCartPO;
import org.springframework.stereotype.Service;

@Service
public class OrderCartDaoServiceImpl extends ServiceImpl<OrderCartDao, OrderCartPO> implements OrderCartDaoService {

}
