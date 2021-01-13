/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.az.java.spring.definition;

import cn.az.java.spring.factory.DefaultUserFactory;
import cn.az.java.spring.factory.UserFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

/**
 * Bean 初始化 Demo
 *
 * @author az
 */
@Configuration
public class BeanInitializationDemo {

    /**
     * <p>@PostConstruct : UserFactory 初始化中...
     * InitializingBean#afterPropertiesSet() : UserFactory 初始化中...
     * 自定义初始化方法 initUserFactory() : UserFactory 初始化中...
     * Spring 应用上下文已启动...
     * cn.az.spring.factory.DefaultUserFactory@7692d9cc
     * Spring 应用上下文准备关闭...</p>
     *
     */
    public static void main(String[] args) {
        // 创建 BeanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 注册 Configuration Class（配置类）
        applicationContext.register(BeanInitializationDemo.class);

        // 启动 Spring 应用上下文
        applicationContext.refresh();
        // 非延迟初始化在 Spring 应用上下文启动完成后，被初始化
        System.out.println("Spring 应用上下文已启动...");
        // 依赖查找 UserFactory
        UserFactory userFactory = applicationContext.getBean(UserFactory.class);
        System.out.println(userFactory);
        System.out.println("Spring 应用上下文准备关闭...");
        // 关闭 Spring 应用上下文
        applicationContext.close();
        System.out.println("Spring 应用上下文已关闭...");
    }

    @Bean(initMethod = "doInit", destroyMethod = "doDestroy")
    @Lazy(value = false) // 上下文启动完成后 再初始化Bean
    public UserFactory userFactory() {
        return new DefaultUserFactory();
    }
}
