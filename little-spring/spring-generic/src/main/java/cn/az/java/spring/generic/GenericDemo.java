package cn.az.java.spring.generic;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author az
 * @since 08/11/20
 */
public class GenericDemo {

    public static void main(String[] args) {
        // Java 7 Diamond 语法
        Collection<String> list = new ArrayList<>();
        list.add("Hello");
        list.add("World");
        // 编译时错误
        // list.add(1);

        // 泛型擦写
        Collection temp = list;
        // 编译通过
        temp.add(1);

        System.out.println(list);
    }
}
