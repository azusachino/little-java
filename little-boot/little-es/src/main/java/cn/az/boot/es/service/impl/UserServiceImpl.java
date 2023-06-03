package cn.az.boot.es.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Service;

import cn.az.boot.es.entity.UserEs;
import cn.az.boot.es.repository.UserRepository;
import cn.az.boot.es.service.UserService;
import jakarta.annotation.Resource;

/**
 * @author Liz
 * @version 2019/11/29
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserRepository userRepository;

    /**
     * Save user es.
     *
     * @param entity the entity
     * @return the user es
     */
    @Override
    public UserEs save(UserEs entity) {
        return userRepository.save(entity);
    }

    /**
     * Find all iterable.
     *
     * @return the iterable
     */
    @Override
    public Iterable<UserEs> findAll() {
        return userRepository.findAll();
    }

    /**
     * Search page.
     *
     * @param query the query
     * @return the page
     */
    @Override
    public Page<UserEs> search(Query query) {
        // return userRepository.search(query);
        return null;
    }
}
