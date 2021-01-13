package cn.az.boot.shiro.repository;

import cn.az.boot.shiro.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * <h3>MySpringBoot</h3>
 *
 * @author : azchino
 * @date : 2019-07-20 17:30
 **/
@Repository
public interface RoleRepo extends JpaRepository<Role, Long> {

}
