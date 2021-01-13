package cn.az.java.spring.lifecycle.demo;

import org.springframework.util.SimpleIdGenerator;

/**
 * @author az
 * @date 2020/4/2
 */
public class UtilDemo {

    public static void main(String[] args) {
        SimpleIdGenerator sig = new SimpleIdGenerator();

        for (int i = 0; i < 10; i++) {
            System.out.println(sig.generateId());
        }
    }
}
