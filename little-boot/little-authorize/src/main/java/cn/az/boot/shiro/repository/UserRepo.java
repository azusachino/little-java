package cn.az.boot.shiro.repository;

import cn.az.boot.shiro.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * <h3>MySpringBoot</h3>
 *
 * @author : azchino
 */
@Repository
public interface UserRepo extends JpaRepository<User, Long> {

    /**
     * Find by username user.
     *
     * @param username the username
     * @return the user
     */
    User findByUsername(String username);
}

