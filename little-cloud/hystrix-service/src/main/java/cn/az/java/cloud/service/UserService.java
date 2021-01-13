package cn.az.java.cloud.service;

import cn.az.java.cloud.domain.RestResponse;
import cn.az.java.cloud.domain.User;

import java.util.List;
import java.util.concurrent.Future;

/**
 * The interface User service.
 *
 * @author azusachino
 * @version 2019 /12/08
 */
public interface UserService {

    /**
     * Gets user.
     *
     * @param id the id
     * @return the user
     */
    RestResponse getUser(Long id);

    /**
     * Gets default user.
     *
     * @param id the id
     * @return the default user
     */
    RestResponse getDefaultUser(Long id);

    /**
     * Gets user by ids.
     *
     * @param ids the ids
     * @return the user by ids
     */
    RestResponse getUserByIds(List<Long> ids);

    /**
     * Gets user exception.
     *
     * @param id the id
     * @return the user exception
     */
    RestResponse getUserException(Long id);

    /**
     * Gets user command.
     *
     * @param id the id
     * @return the user command
     */
    RestResponse getUserCommand(Long id);

    /**
     * Gets user cache.
     *
     * @param id the id
     * @return rest response.
     */
    RestResponse getUserCache(Long id);

    /**
     * Remove cache rest response.
     *
     * @param id the id
     * @return rest response.
     */
    RestResponse removeCache(Long id);

    /**
     * Gets user future.
     *
     * @param id the id
     * @return the user future
     */
    Future<User> getUserFuture(Long id);

}
