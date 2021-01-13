package cn.az.boot.validation.controller;

import cn.az.boot.validation.domain.Person;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * {@link Person}
 *
 * @author <a href="mailto:azusa146@gmail.com">az</a>
 * @see Person
 * @since 2020-03-12
 */
@RestController
@RequestMapping("/person")
public class PersonController {

    private Map<String, String> data;

    @PostConstruct
    public void init() {
        data = new HashMap<>();
    }


    @GetMapping("/{name}")
    public String get(@PathVariable("name") String name) {
        return data.getOrDefault(name, "sorry not found");
    }

    @PostMapping("/save")
    public Person save(@Validated({Person.GroupOrder.class}) @RequestBody Person person) {
        data.put(person.getName(), person.toString());
        return person;
    }
}
