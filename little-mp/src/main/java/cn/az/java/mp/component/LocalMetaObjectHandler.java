package cn.az.java.mp.component;

import cn.az.java.mp.entity.User;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.core.handlers.StrictFill;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author az
 * @since 2020-04-03
 */
@Component
public class LocalMetaObjectHandler implements MetaObjectHandler {

    /**
     * 插入元对象字段填充（用于插入时对公共字段的填充）
     *
     * @param metaObject 元对象
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        List<StrictFill> strictFills = new ArrayList<>();
        strictFills.add(StrictFill.of("create_time", LocalDateTime.class, LocalDateTime::now));
        strictUpdateFill(new TableInfo(User.class), metaObject, strictFills);
    }

    /**
     * 更新元对象字段填充（用于更新时对公共字段的填充）
     *
     * @param metaObject 元对象
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        List<StrictFill> strictFills = new ArrayList<>();
        strictFills.add(StrictFill.of("update_time", LocalDateTime.class, LocalDateTime::now));
        strictUpdateFill(findTableInfo(metaObject), metaObject, strictFills);
    }
}
