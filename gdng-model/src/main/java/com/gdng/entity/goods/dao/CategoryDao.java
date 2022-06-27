package com.gdng.entity.goods.dao;

import com.gdng.entity.goods.po.CategoryPO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 类目表 Mapper 接口
 * </p>
 *
 * @author gc
 * @since 2022-06-26
 */
@Mapper
public interface CategoryDao extends BaseMapper<CategoryPO> {

}
