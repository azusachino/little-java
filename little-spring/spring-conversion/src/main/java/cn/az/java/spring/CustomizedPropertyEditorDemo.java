package cn.az.java.spring;

import cn.az.java.spring.ioc.domain.User;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author az
 * @since 08/10/20
 */
public class CustomizedPropertyEditorDemo {

    public static void main(String[] args) {

        // 创建并且启动 BeanFactory 容器
        ConfigurableApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/META-INF/property-editors-context.xml");

        // AbstractApplicationContext -> "conversionService" ConversionService Bean
        // -> ConfigurableBeanFactory#setConversionService(ConversionService)
        // -> AbstractAutowireCapableBeanFactory.instantiateBean
        // -> AbstractBeanFactory#getConversionService ->
        // BeanDefinition -> BeanWrapper -> 属性转换（数据来源：PropertyValues）->
        // setPropertyValues(PropertyValues) -> TypeConverter#convertIfNecessnary
        // TypeConverterDelegate#convertIfNecessnary  -> PropertyEditor or ConversionService

        User user = applicationContext.getBean("user", User.class);

        System.out.println(user);

        // 显示地关闭 Spring 应用上下文
        applicationContext.close();
    }

}
