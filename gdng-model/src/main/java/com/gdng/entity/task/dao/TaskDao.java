package com.gdng.entity.task.dao;

import com.gdng.entity.task.po.TaskPO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 任务表 Mapper 接口
 * </p>
 *
 * @author gc
 * @since 2022-07-22
 */
@Mapper
public interface TaskDao extends BaseMapper<TaskPO> {

}
