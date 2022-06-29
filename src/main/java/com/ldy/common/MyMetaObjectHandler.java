package com.ldy.common;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("公共字段自动填充insert...");
        log.info(metaObject.toString());
        metaObject.setValue("createTime", LocalDateTime.now());
        metaObject.setValue("updateTime", LocalDateTime.now());

        //从当前线程中的ThreadLocal中取id
        Long id = BaseContext.getCurrentId();
        metaObject.setValue("createUser", id);
        metaObject.setValue("updateUser", id);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("公共字段自动填充update...");
        log.info(metaObject.toString());
        metaObject.setValue("updateTime", LocalDateTime.now());
        //从当前线程中的ThreadLocal中取id
        Long id = BaseContext.getCurrentId();
        metaObject.setValue("updateUser", id);
    }
}
