package cn.az.boot.dao;

import cn.az.boot.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The interface User dao.
 *
 * @author azusachino
 * @version 2019 /12/09
 */
@Repository
public interface UserDao extends MongoRepository<User,String> {


    /**
     * Find by age between list.
     *
     * @param from the from
     * @param to   the to
     * @return the list
     */
    List<User> findByAgeBetween(Integer from, Integer to);

    /**
     * Find by age between and name equals and description is like list.
     *
     * @param from        the from
     * @param to          the to
     * @param name        the name
     * @param description the description
     * @return the list
     */
    List<User> findByAgeBetweenAndNameEqualsAndDescriptionIsLike(Integer from, Integer to, String name, String description);

    /**
     * Find by description is like list.
     *
     * @param description the description
     * @return the list
     */
    List<User> findByDescriptionIsLike(String description);

    /**
     * Find by name equals list.
     *
     * @param name the name
     * @return the list
     */
    List<User> findByNameEquals(String name);
}
