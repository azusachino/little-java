package cn.az.java.cloud.api;

import cn.az.java.cloud.domain.Admin;
import cn.az.java.cloud.fallback.UserServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collection;

/**
 * @author az
 * @since 2020-04-15
 */
@FeignClient(name = "${user.service.name}", fallback = UserServiceFallback.class)
public interface UserService {

    /**
     * 保存用户
     *
     * @param admin user
     * @return boolean
     */
    @PostMapping("/user/save")
    Boolean saveUser(Admin admin);


    /**
     * 查询所有的用户列表
     *
     * @return non-null
     */
    @GetMapping("/user/find/all")
    Collection<Admin> findAll();
}
