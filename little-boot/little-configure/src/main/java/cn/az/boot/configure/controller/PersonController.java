package cn.az.boot.configure.controller;

import cn.az.boot.configure.domain.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * The type Person controller.
 *
 * @author az
 * @date 2020/3/14
 */
@RestController
public class PersonController implements EnvironmentAware {

    @Autowired
    private Person person;

    @Value("${person.id}")
    private String id;
    /**
     * ${xxx:defaultValue}
     */
    @Value("${person.name:AZ}")
    private String name;

    private Integer age;


    @GetMapping("/person1")
    @Profile("dev")
    public Person p1() {
        return person;
    }

    @GetMapping("/person2")
    @Profile("prod")
    public Person p2() {
        return person;
    }

    @GetMapping("/person/data")
    public Map<String, Object> data() {

        Map<String, Object> data = new LinkedHashMap<>();

        data.put("id", id);
        data.put("name", name);
        data.put("age", age);

        return data;
    }

    @Override
    public void setEnvironment(Environment environment) {

        this.age = environment.getProperty("person.age", Integer.class);

    }
}
