package cn.az.java.cloud.controller;

import cn.az.java.cloud.domain.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Person Controller
 *
 * @author <a href="mailto:azusa146@gmail.com">az</a>
 * @see Person
 * @since 2020-03-19
 */
@RestController
@RequestMapping("person")
public class PersonController {

    private final Person person;

    @Autowired
    public PersonController(Person person) {
        this.person = person;
    }

    @GetMapping
    public Person get() {
        return person;
    }
}
