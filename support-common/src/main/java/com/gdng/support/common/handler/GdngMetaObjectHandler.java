package com.gdng.support.common.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.gdng.support.common.dto.UserDTO;
import com.gdng.support.common.spring.SpringContextHolder;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

import static com.gdng.support.common.constant.MapperConstant.*;

@Component
public class GdngMetaObjectHandler implements MetaObjectHandler {

    public static final int OPTIMISTIC_DEFAULT = 0;

    @Override
    public void insertFill(MetaObject metaObject) {
        String uid = getUid();
        boolean hasCreateTime = metaObject.hasSetter(CREATE_TIME);
        if (hasCreateTime) {
            metaObject.setValue(CREATE_TIME, new Date());
        }
        boolean hasCreator = metaObject.hasSetter(CREATOR);
        if (hasCreator) {
            metaObject.setValue(CREATOR, uid);
        }
        boolean hasUpdateTime = metaObject.hasSetter(UPDATE_TIME);
        if (hasUpdateTime) {
            metaObject.setValue(UPDATE_TIME, new Date());
        }
        boolean hasUpdator = metaObject.hasSetter(UPDATOR);
        if (hasUpdator) {
            metaObject.setValue(UPDATOR, uid);
        }
        boolean hasOptimistic = metaObject.hasSetter(OPTIMISTIC);
        if (hasOptimistic) {
            metaObject.setValue(OPTIMISTIC, OPTIMISTIC_DEFAULT);
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        String uid = getUid();
        boolean hasUpdateTime = metaObject.hasSetter(UPDATE_TIME);
        if (hasUpdateTime) {
            metaObject.setValue(UPDATE_TIME, new Date());
        }
        boolean hasUpdator = metaObject.hasSetter(UPDATOR);
        if (hasUpdator) {
            metaObject.setValue(UPDATOR, uid);
        }
    }

    private String getUid() {
        UserDTO user = SpringContextHolder.getUser();
        return user == null ? "system" : user.getId();
    }
}
