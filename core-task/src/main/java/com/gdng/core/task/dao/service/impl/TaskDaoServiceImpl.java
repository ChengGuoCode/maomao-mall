package com.gdng.core.task.dao.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gdng.core.task.dao.service.TaskDaoService;
import com.gdng.entity.task.dao.TaskDao;
import com.gdng.entity.task.po.TaskPO;
import org.springframework.stereotype.Service;

/**
 * @Auther: guocheng
 * @CreateDate: 2022/7/20 10:05
 * @Description:
 * @Version: 1.0.0
 */
@Service
public class TaskDaoServiceImpl extends ServiceImpl<TaskDao, TaskPO> implements TaskDaoService {
}
