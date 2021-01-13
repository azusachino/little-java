package cn.az.java.modular;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Collections;

/**
 * @author az
 */
public class UnnamedModuleDemo {

    public static void main(String[] args) {
        MapUtils.isEmpty(Collections.emptyMap());
        StringUtils.isBlank("OK");
        ConfigurableApplicationContext context = new AnnotationConfigApplicationContext();
        context.close();
    }
}
