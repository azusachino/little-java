package cn.az.java.mp.mapper;

import cn.az.java.mp.entity.User;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * The interface User mapper.
 *
 * @author az
 * @since 2020-04-03
 */
public interface UserMapper extends BaseMapper<User> {

    /**
     * Constants.WRAPPER = ew => auto created sql segment
     *
     * @param wrapper the wrapper
     * @return the list
     */
    @Select("select * from user ${ew.customSqlSegment}")
    List<User> mySelectList(@Param(Constants.WRAPPER) Wrapper<User> wrapper);
}
