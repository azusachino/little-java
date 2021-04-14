package cn.az.boot.mybatis.dao;

import cn.az.boot.mybatis.domain.User;
import org.apache.ibatis.annotations.*;

/**
 * The interface User mapper.
 *
 * @author az
 */
@Mapper
public interface UserMapper {

    /**
     * Select user user.
     *
     * @param id the id
     * @return the user
     */
    @Select("select * from user WHERE id = #{id}")
    @Results(value = {@Result(property = "id", column = "ID")})
    User selectUser(int id);
}
