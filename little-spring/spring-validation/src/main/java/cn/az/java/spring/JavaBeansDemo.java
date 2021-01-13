package cn.az.java.spring;

import cn.az.java.spring.ioc.domain.User;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.util.stream.Stream;

/**
 * @author az
 * @since 08/05/20
 */
public class JavaBeansDemo {

    public static void main(String[] args) throws IntrospectionException {

        // stopClass 排除（截止）类
        BeanInfo beanInfo = Introspector.getBeanInfo(User.class, Object.class);
        // 属性描述符 PropertyDescriptor

        // 所有 Java 类均继承 java.lang.Object
        // class 属性来自于 Object#getClass() 方法
        //                    propertyDescriptor.getReadMethod(); // Getter 方法
        //                    propertyDescriptor.getWriteMethod(); // Setter 方法
        Stream.of(beanInfo.getPropertyDescriptors())
                .forEach(System.out::println);

        // 输出 User 定义的方法 MethodDescriptor
        Stream.of(beanInfo.getMethodDescriptors()).forEach(System.out::println);

    }
}
