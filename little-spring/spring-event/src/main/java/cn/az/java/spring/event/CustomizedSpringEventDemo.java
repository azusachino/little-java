package cn.az.java.spring.event;

import cn.az.java.spring.event.listener.MySpringEventListener;
import org.springframework.context.support.GenericApplicationContext;

/**
 * @author az
 * @since 08/13/20
 */
public class CustomizedSpringEventDemo {

    public static void main(String[] args) {

        GenericApplicationContext context = new GenericApplicationContext();

        // 1.添加自定义 Spring 事件监听器
        // ListenerRetriever -> 0 .. N 个 ApplicationListener<MySpringEvent> 实例
        // MySpringEvent 以及它子孙类
        context.addApplicationListener(new MySpringEventListener());

        context.addApplicationListener(event -> System.out.println("Event : " + event));

        // 2.启动 Spring 应用上下文
        context.refresh();

        // 3. 发布自定义 Spring 事件
        // ListenerCacheKey -> MySpringEvent
        context.publishEvent(new MySpringEvent("Hello,World"));
        context.publishEvent(new AnotherSpringEvent("2020"));

        // 4. 关闭 Spring 应用上下文
        context.close();
    }
}
