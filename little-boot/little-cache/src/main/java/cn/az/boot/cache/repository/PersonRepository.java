package cn.az.boot.cache.repository;

import cn.az.boot.cache.entity.Person;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * The interface Person repository.
 *
 * @author az
 */
@NoRepositoryBean
public interface PersonRepository {

    /**
     * Find person person.
     *
     * @param id the id
     * @return the person
     */
    Person findPerson(String id);

    /**
     * Save person person.
     *
     * @param entity the entity
     * @return the person
     */
    Person savePerson(Person entity);

    /**
     * delete person
     *
     * @param id ID
     */
    Boolean deletePerson(String id);
}
