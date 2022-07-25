package com.gdng.core.payment.dao.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gdng.core.payment.dao.service.TaskPayDaoService;
import com.gdng.entity.payment.dao.TaskPayDao;
import com.gdng.entity.payment.po.TaskPayPO;
import org.springframework.stereotype.Service;

@Service
public class TaskPayDaoServiceImpl extends ServiceImpl<TaskPayDao, TaskPayPO> implements TaskPayDaoService {

}
