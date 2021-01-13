package cn.az.boot.aop.test;

import cn.az.boot.aop.annotation.DoSomething;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;

/**
 * @author az
 * @since 2020-04-20
 */
@Configuration
public class Something {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(Something.class);

        applicationContext.refresh();

        Something something = applicationContext.getBean(Something.class);
        something.print("yes");

        applicationContext.close();
    }

    @DoSomething(before = "water", after = "melon")
    public void print(String s) {
        System.out.println(s);
    }
}
