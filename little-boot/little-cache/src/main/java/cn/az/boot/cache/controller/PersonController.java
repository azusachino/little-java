package cn.az.boot.cache.controller;

import cn.az.boot.cache.entity.Person;
import cn.az.boot.cache.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * The type Person controller.
 *
 * @author az
 * @date 2020-03-11
 */
@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonRepository personRepository;

    /**
     * Save person.
     *
     * @param entity the entity
     * @return the person
     */
    @PostMapping("/save")
    public Person save(@RequestBody Person entity) {
        return personRepository.savePerson(entity);
    }

    /**
     * Get person.
     *
     * @param id the id
     * @return the person
     */
    @GetMapping("/{id}")
    public Person get(@PathVariable("id") String id) {
        return personRepository.findPerson(id);
    }

    @DeleteMapping("/{id}")
    public Boolean delete(@PathVariable("id") String id) {
        return personRepository.deletePerson(id);
    }
}

