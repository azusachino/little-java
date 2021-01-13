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
package cn.az.java.spring.lifecycle;

import cn.az.java.spring.lifecycle.domain.User;
import cn.az.java.spring.lifecycle.domain.UserHolder;
import org.springframework.beans.factory.config.DestructionAwareBeanPostProcessor;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.CommonAnnotationBeanPostProcessor;

/**
 * Bean的完整生命周期展示
 *
 * @see InstantiationAwareBeanPostProcessor
 * @see DestructionAwareBeanPostProcessor
 * @see CommonAnnotationBeanPostProcessor
 * @author az
 */
public class BeanLifecycleDemo {

    public static void main(String[] args) throws InterruptedException {

        // AbstractAutowireCapableBeanFactory | ConfigurableListableBeanFactory, BeanDefinitionRegistry
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 添加 BeanPostProcessor 实现 MyInstantiationAwareBeanPostProcessor => InstantiationAwareBeanPostProcessor
        // 实例化和初始化的回调钩子
        beanFactory.addBeanPostProcessor(new MyInstantiationAwareBeanPostProcessor());

        // 添加 MyDestructionAwareBeanPostProcessor 执行销毁前回调 => DestructionAwareBeanPostProcessor
        // 销毁的回调钩子
        beanFactory.addBeanPostProcessor(new MyDestructionAwareBeanPostProcessor());

        // 添加 CommonAnnotationBeanPostProcessor 解决 @PostConstruct @PreDestroy
        // 由于当前是DefaultListableBeanFactory, 需要启用注解环境的postProcessor
        // 	public CommonAnnotationBeanPostProcessor() {
        //		setOrder(Ordered.LOWEST_PRECEDENCE - 3);
        //		setInitAnnotationType(PostConstruct.class);
        //		setDestroyAnnotationType(PreDestroy.class);
        //		ignoreResourceType("javax.xml.ws.WebServiceContext");
        //	}
        beanFactory.addBeanPostProcessor(new CommonAnnotationBeanPostProcessor());

        // xml BD 来源 => 类似于 ClassPathXmlApplicationContext
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        String[] locations = {"META-INF/dependency-lookup-context.xml", "META-INF/bean-constructor-dependency-injection.xml"};
        int beanNumbers = beanDefinitionReader.loadBeanDefinitions(locations);
        System.out.println("已加载 BeanDefinition 数量：" + beanNumbers);

        // 显示地执行 preInstantiateSingletons()
        // SmartInitializingSingleton 通常在 Spring ApplicationContext 场景使用
        // preInstantiateSingletons 将已注册的 BeanDefinition 初始化成 Spring Bean
        beanFactory.preInstantiateSingletons();

        // 通过 Bean Id 和类型进行依赖查找
        User user = beanFactory.getBean("user", User.class);
        System.out.println(user);

        User superUser = beanFactory.getBean("superUser", User.class);
        System.out.println(superUser);

        // 构造器注入按照类型注入，resolveDependency
        UserHolder userHolder = beanFactory.getBean("userHolder", UserHolder.class);

        System.out.println(userHolder);

        // 执行 Bean 销毁（容器内）
        beanFactory.destroyBean("userHolder", userHolder);
        // Bean 销毁并不意味着 Bean 垃圾回收了
        System.out.println(userHolder);

        // 销毁 BeanFactory 中的单例 Bean
        beanFactory.destroySingletons();
        // 强制 GC
        System.gc();
        // 等待一段时间
        Thread.sleep(1000L);
        // 强制 GC
        System.gc();
    }
}
