package cn.az.boot.mapper;

import cn.az.boot.entity.User;
import cn.az.boot.model.MyPage;
import cn.az.boot.model.MyParam;
import cn.az.boot.model.UserChildren;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * The interface User mapper.
 *
 * @author azusachino
 * @version 12 /13/2019
 */
@Repository
public interface UserMapper extends BaseMapper<User> {

    /**
     * My select page my page.
     *
     * @param myPage  the my page
     * @param myParam the my param
     * @return the my page
     */
    MyPage<User> mySelectPage(@Param("page") MyPage<User> myPage, @Param("param") MyParam myParam);

    /**
     * User children page my page.
     *
     * @param myPage the my page
     * @return the my page
     */
    @ResultMap("userChildrenMap")
    @Select("<script>select u.id,u.name,u.email,u.age,c.id as \"c_id\",c.name as \"c_name\",c.user_id as \"c_user_id\" " +
        "from user u " +
        "left join child c on c.user_id = u.id " +
        "<where>" +
        "<if test=\"selectInt != null\"> " +
        "and u.age = #{selectInt} " +
        "</if> " +
        "<if test=\"selectStr != null and selectStr != ''\"> " +
        "and c.name = #{selectStr} " +
        "</if> " +
        "</where>" +
        "</script>")
    MyPage<UserChildren> userChildrenPage(MyPage<UserChildren> myPage);


    /**
     * My select page map my page.
     *
     * @param myPage the my page
     * @param param  the param
     * @return the my page
     */
    MyPage<User> mySelectPageMap(@Param("pg") MyPage<User> myPage, @Param("map") Map<?, ?> param);

    /**
     * My select map list.
     *
     * @param param the param
     * @return the list
     */
    List<User> mySelectMap(Map<?, ?> param);

    /**
     * My page select list.
     *
     * @param myPage the my page
     * @return the list
     */
    List<User> myPageSelect(MyPage<User> myPage);

    /**
     * Page select list.
     *
     * @param myPage the my page
     * @return the list
     */
    List<User> iPageSelect(IPage<User> myPage);

    /**
     * Row bound list list.
     *
     * @param rowBounds the row bounds
     * @param map       the map
     * @return the list
     */
    List<User> rowBoundList(RowBounds rowBounds, Map<?, ?> map);
}
