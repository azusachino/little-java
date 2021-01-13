package cn.az.java.spring;

import cn.az.java.spring.ioc.domain.User;
import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.beans.PropertyEditorRegistry;

/**
 * @author az
 * @since 08/10/20
 */
public class CustomizedPropertyEditorRegistrar implements PropertyEditorRegistrar {

    @Override
    public void registerCustomEditors(PropertyEditorRegistry registry) {
        // 1. 通用类型转换
        // 2. Java Bean 属性类型转换
        registry.registerCustomEditor(User.class, "context", new String2PropertiesPropertyEditor());
    }
}
