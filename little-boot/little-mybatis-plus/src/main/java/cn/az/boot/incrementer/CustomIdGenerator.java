package cn.az.boot.incrementer;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author azusachino
 * @version 12/13/2019
 */
@Slf4j
public class CustomIdGenerator {

    private final AtomicLong atomicLong = new AtomicLong(1);

    public Long nextId(Object entity) {
        MetaObject metaObject = SystemMetaObject.forObject(entity);
        String name = (String) metaObject.getValue("name");
        final long id = atomicLong.getAndAdd(1);
        log.info("开始为: {},生成主键值为: {}", name, id);
        return id;
    }
}
