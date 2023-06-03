package cn.az.boot.test.junit;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.TestPropertySources;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.util.Assert;

import cn.az.boot.test.config.PersonConfiguration;
import cn.az.boot.test.domain.Person;
import cn.az.boot.test.listener.PersonIntegrationTestListener;

/**
 * @author az
 */
@ContextHierarchy(@ContextConfiguration(classes = PersonConfiguration.class))
@SpringBootTest(classes = PersonConfiguration.class)
@TestExecutionListeners(listeners = {
        PersonIntegrationTestListener.class,
        DependencyInjectionTestExecutionListener.class })
@TestPropertySources(value = { @TestPropertySource(properties = { "aaa", "water" }) })
public class PersonIntegrationTet {

    @Autowired
    private Person person;

    @Test
    public void testMainPerson() {
        Assert.notNull(person, "watermelon");

    }
}
