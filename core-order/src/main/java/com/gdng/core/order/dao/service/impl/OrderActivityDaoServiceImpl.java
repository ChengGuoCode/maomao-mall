package com.gdng.core.order.dao.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gdng.core.order.dao.service.OrderActivityDaoService;
import com.gdng.entity.order.dao.OrderActivityDao;
import com.gdng.entity.order.po.OrderActivityPO;
import org.springframework.stereotype.Service;

@Service
public class OrderActivityDaoServiceImpl extends ServiceImpl<OrderActivityDao, OrderActivityPO> implements OrderActivityDaoService {
}
