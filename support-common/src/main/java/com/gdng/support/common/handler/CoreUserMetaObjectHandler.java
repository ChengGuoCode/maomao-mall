package com.gdng.support.common.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.gdng.support.common.dto.UserDTO;
import com.gdng.support.common.util.JacksonUtil;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Date;

import static com.gdng.support.common.constant.MapperConstant.*;

@Component
public class CoreUserMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        String uid = getUid();
        metaObject.setValue(CREATE_TIME, new Date());
        metaObject.setValue(CREATOR, uid);
        metaObject.setValue(UPDATE_TIME, new Date());
        metaObject.setValue(UPDATOR, uid);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        String uid = getUid();
        metaObject.setValue(UPDATE_TIME, new Date());
        metaObject.setValue(UPDATOR, uid);
    }

    private String getUid() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication == null ? "system" : JacksonUtil.jsonToBean(authentication.getPrincipal().toString(), UserDTO.class).getId();
    }
}
