package cn.az.java.spring.event;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;


/**
 * @author az
 * @since 08/13/20
 */
@EnableAsync
public class AnnotatedAsyncEventHandlerDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext configApplicationContext = new AnnotationConfigApplicationContext();

        configApplicationContext.register(AnnotatedAsyncEventHandlerDemo.class);

        configApplicationContext.refresh();

        configApplicationContext.publishEvent(new MySpringEvent("Hello Spring Event!"));

        configApplicationContext.close();
    }

    @Async
    @EventListener
    public void onEvent(MySpringEvent event) {
        System.out.printf("[线程 ： %s] onEvent方法监听到事件 : %s\n", Thread.currentThread().getName(), event);
    }

    @Bean
    public TaskExecutor taskExecutor() {
        //org.springframework.beans.factory.NoSuchBeanDefinitionException: No qualifying bean of type 'org.springframework.core.task.TaskExecutor' available
        return new SimpleAsyncTaskExecutor();
    }
}
