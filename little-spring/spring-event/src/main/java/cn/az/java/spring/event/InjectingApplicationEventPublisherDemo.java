package cn.az.java.spring.event;

import cn.az.java.spring.event.listener.MySpringEventListener;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.NonNull;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * @author az
 * @since 08/13/20
 */
public class InjectingApplicationEventPublisherDemo implements ApplicationEventPublisherAware,
    ApplicationContextAware, BeanPostProcessor {

    @Resource
    private ApplicationEventPublisher applicationEventPublisher;

    @Resource
    private ApplicationContext applicationContext;

    @PostConstruct
    public void init() {
        //# 3
        applicationEventPublisher.publishEvent(new MySpringEvent("The event from @Autowired ApplicationEventPublisher"));
        // #4
        applicationContext.publishEvent(new MySpringEvent("The event from @Autowired ApplicationContext"));
    }

    public static void main(String[] args) {

        // 创建注解驱动 Spring 应用上下文
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        // 注册 Configuration Class
        context.register(InjectingApplicationEventPublisherDemo.class);

        // 增加 Spring 事件监听器
        context.addApplicationListener(new MySpringEventListener());

        // 启动 Spring 应用上下文
        context.refresh();

        // 关闭 Spring 应用上下文
        context.close();
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) { // #1
        applicationEventPublisher.publishEvent(new MySpringEvent("The event from ApplicationEventPublisherAware"));
    }

    @Override
    public void setApplicationContext(@NonNull ApplicationContext applicationContext) throws BeansException { // #2
        applicationEventPublisher.publishEvent(new MySpringEvent("The event from ApplicationContextAware"));
    }
}
