package cn.az.boot.mybatis.dao;

import cn.az.boot.mybatis.domain.User;

/**
 * @author az
 */
public interface UserDao {

    User selectOneUser(int id);
}
