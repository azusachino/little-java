package cn.az.boot.shiro.service;

import cn.az.boot.shiro.repository.UserRepo;
import cn.az.boot.shiro.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <h3>MySpringBoot</h3>
 *
 * @author : azchino
 * @date : 2019-07-20 21:45
 **/
@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public User findByUsername(String username) {
        return userRepo.findByUsername(username);
    }
}
