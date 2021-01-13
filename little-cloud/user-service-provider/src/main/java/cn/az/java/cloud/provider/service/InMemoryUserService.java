package cn.az.java.cloud.provider.service;

import cn.az.cloud.api.UserService;
import cn.az.cloud.domain.Admin;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author az
 * @since 2020-04-15
 */
@Service("inMemoryUserService")
public class InMemoryUserService implements UserService {

    private final Map<Long, Admin> repo = new ConcurrentHashMap<>();


    /**
     * 保存用户
     *
     * @param admin user
     * @return boolean
     */
    @Override
    public Boolean saveUser(Admin admin) {
        return repo.putIfAbsent(admin.getId(), admin) == null;
    }

    /**
     * 查询所有的用户列表
     *
     * @return non-null
     */
    @Override
    public Collection<Admin> findAll() {
        return repo.values();
    }

    @PostConstruct
    public void init() {
        repo.putIfAbsent(1L, new Admin(1L, "azusa"));
        repo.putIfAbsent(2L, new Admin(2L, "chino"));
    }
}
