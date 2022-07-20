package com.gdng.core.task.dao.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gdng.core.task.dao.service.TaskRecordDaoService;
import com.gdng.entity.task.dao.TaskRecordDao;
import com.gdng.entity.task.po.TaskRecordPO;
import org.springframework.stereotype.Service;

/**
 * @Auther: guocheng
 * @CreateDate: 2022/7/20 10:09
 * @Description:
 * @Version: 1.0.0
 */
@Service
public class TaskRecordDaoServiceImpl extends ServiceImpl<TaskRecordDao, TaskRecordPO> implements TaskRecordDaoService {
}
