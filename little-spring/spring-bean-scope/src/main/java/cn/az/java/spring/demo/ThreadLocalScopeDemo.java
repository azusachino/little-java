package cn.az.java.spring.demo;

import cn.az.java.spring.domain.User;
import cn.az.java.spring.scope.ThreadLocalScope;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

/**
 * @author az
 * @since 07/30/20
 */
public class ThreadLocalScopeDemo {

    @Bean
    @Scope(ThreadLocalScope.THREAD_LOCAL_SCOPE)
    public User user() {
        return createUser();
    }

    private static User createUser() {
        User user = new User();
        user.setId(System.nanoTime());
        return user;
    }

    public static void main(String[] args) {

        // 创建 BeanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 注册 Configuration Class（配置类） -> Spring Bean
        applicationContext.register(ThreadLocalScopeDemo.class);

        applicationContext.addBeanFactoryPostProcessor(beanFactory -> {
            // 注册自定义 scope
            beanFactory.registerScope(ThreadLocalScope.THREAD_LOCAL_SCOPE, new ThreadLocalScope());
        });

        // 启动 Spring 应用上下文
        applicationContext.refresh();

        scopedBeansByLookup(applicationContext);

        // 关闭 Spring 应用上下文
        applicationContext.close();
    }

    private static void scopedBeansByLookup(AnnotationConfigApplicationContext applicationContext) {

        for (int i = 0; i < 3; i++) {
            // 不同的线程 存储不同的对象
            Thread thread = new Thread(() -> {
                // user 是共享 Bean 对象
                User user = applicationContext.getBean("user", User.class);
                System.out.printf("[Thread id :%d] user = %s%n", Thread.currentThread().getId(), user);
            });

            // 启动线程
            thread.start();
            // 强制线程执行完成
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
