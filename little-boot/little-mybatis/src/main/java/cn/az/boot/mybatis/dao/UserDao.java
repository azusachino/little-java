package cn.az.boot.mybatis.dao;

import cn.az.boot.mybatis.domain.User;

/**
 * @author az
 * @date 2020-03-09
 */
public interface UserDao {

    User selectOneUser(int id);
}
