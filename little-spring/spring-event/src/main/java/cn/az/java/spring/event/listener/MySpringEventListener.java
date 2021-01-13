package cn.az.java.spring.event.listener;

import cn.az.java.spring.event.MySpringEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.lang.NonNull;

/**
 * @author az
 * @since 08/13/20
 */
public class MySpringEventListener implements ApplicationListener<MySpringEvent> {

    @Override
    public void onApplicationEvent(@NonNull MySpringEvent event) {
        System.out.printf("[线程 ： %s] 监听到事件 : %s\n", Thread.currentThread().getName(), event);
    }
}
