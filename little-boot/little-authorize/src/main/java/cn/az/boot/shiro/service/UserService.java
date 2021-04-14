package cn.az.boot.shiro.service;

import cn.az.boot.shiro.model.User;
import cn.az.boot.shiro.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <h3>MySpringBoot</h3>
 *
 * @author : azchino
 **/
@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public User findByUsername(String username) {
        return userRepo.findByUsername(username);
    }
}
