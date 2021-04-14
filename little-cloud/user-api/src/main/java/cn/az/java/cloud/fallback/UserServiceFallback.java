package cn.az.java.cloud.fallback;

import cn.az.java.cloud.api.UserService;
import cn.az.java.cloud.domain.Admin;

import java.util.Collection;
import java.util.Collections;

/**
 * @author az
 * @since 2020-04-15
 */
public class UserServiceFallback implements UserService {

    /**
     * 保存用户
     *
     * @param admin user
     * @return boolean
     */
    @Override
    public Boolean saveUser(Admin admin) {
        return false;
    }

    /**
     * 查询所有的用户列表
     *
     * @return non-null
     */
    @Override
    public Collection<Admin> findAll() {
        Admin u = new Admin(999L, "fallback");
        return Collections.singleton(u);
    }

}
