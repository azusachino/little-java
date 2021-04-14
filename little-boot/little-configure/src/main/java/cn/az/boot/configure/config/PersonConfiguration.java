package cn.az.boot.configure.config;

import cn.az.boot.configure.domain.Person;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;

/**
 * @author az
 */
@Configuration
@EnableConfigurationProperties(Person.class)
public class PersonConfiguration implements BeanFactoryAware {

    private BeanFactory beanFactory;

    @Bean("person")
    @Profile("dev")
    public Person person1() {
        Person p = new Person();
        p.setName("dev");
        p.setAge(30);
        return p;
    }

    @Bean("az")
    @Profile("prod")
    public Person person2() {
        Person p = new Person();
        p.setName("prod");
        p.setAge(20);
        return p;
    }

    @Bean("p2")
    public Person p2(@Value("${person.id:12345}") Integer id,
                     @Value("${person.age:${my.person.age:99}}") Integer age,
                     @Value("${person.name}") String name) {
        return new Person(id, age, name);
    }

    @Bean("p3")
    public Person p3(Environment environment) {
        Integer id = environment.getProperty("person.age", Integer.class);
        Integer age = environment.getProperty("person.id", Integer.class);
        String name = environment.getProperty("person.name", String.class);

        Person p = new Person();
        p.setId(id);
        p.setAge(age);
        p.setName(name);

        return p;
    }

    /**
     * Callback that supplies the owning factory to a bean instance.
     * <p>Invoked after the population of normal bean properties
     * but before an initialization callback such as
     * {@link InitializingBean#afterPropertiesSet()} or a custom init-method.
     *
     * @param beanFactory owning BeanFactory (never {@code null}).
     *                    The bean can immediately call methods on the factory.
     * @throws BeansException in case of initialization errors
     * @see BeanInitializationException
     */
    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        Environment environment = beanFactory.getBean(ConfigurableApplicationContext.ENVIRONMENT_BEAN_NAME, Environment.class);
        this.beanFactory = beanFactory;
    }

    @Bean
    @ConfigurationProperties("user")
    @ConditionalOnProperty(value = "person.id", matchIfMissing = true, havingValue = "123433")
    public Person p4() {
        beanFactory.containsBean("user");
        return new Person();
    }
}
