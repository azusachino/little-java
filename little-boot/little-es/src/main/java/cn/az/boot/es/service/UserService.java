package cn.az.boot.es.service;

import cn.az.boot.es.entity.UserEs;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.core.query.Query;

/**
 * The interface User service.
 *
 * @author Liz
 * @version 2019 /11/29
 */
public interface UserService {
    /**
     * Save user es.
     *
     * @param entity the entity
     * @return the user es
     */
    UserEs save(UserEs entity);

    /**
     * Find all iterable.
     *
     * @return the iterable
     */
    Iterable<UserEs> findAll();

    /**
     * Search page.
     *
     * @param query the query
     * @return the page
     */
    Page<UserEs> search(Query query);
}
