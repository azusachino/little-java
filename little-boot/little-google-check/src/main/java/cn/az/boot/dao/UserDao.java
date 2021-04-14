package cn.az.boot.dao;

import cn.az.boot.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author azusachino
 * @version 12/16/2019
 */
@Repository
@Mapper
public interface UserDao extends BaseMapper<User> {

}
