package cn.az.boot.mybatis.controller;

import cn.az.boot.mybatis.domain.User;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author az
 */
@RestController
public class UserController {

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    @GetMapping("/user/{id}")
    public User user(@PathVariable("id") int id) {
        return sqlSessionTemplate.selectOne("cn.az.boot.mybatis.dao.UserMapper.selectUser", 1);
    }
}
