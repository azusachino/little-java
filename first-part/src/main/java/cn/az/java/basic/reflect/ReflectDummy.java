package cn.az.java.basic.reflect;

import java.lang.reflect.Constructor;

/**
 * @author az
 * @since 2020-04-09
 */
public class ReflectDummy {
    public static void main(String[] args) throws Exception {

        Class<?> c1 = Dummy.class;
        Class<?> c2 = new Dummy().getClass();
        Class<?> c3 = Class.forName("cn.az.java.basic.reflect.ReflectDummy");
        Constructor<?> ctr = c1.getConstructor();
        System.out.println(c1.newInstance());
        System.out.println(ctr.newInstance());
    }
}
