package cn.az.boot;

import cn.az.boot.entity.User;
import cn.az.boot.mapper.UserMapper;
import cn.az.boot.model.MyPage;
import cn.az.boot.model.MyParam;
import cn.az.boot.model.UserChildren;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.api.Assert;
import com.baomidou.mybatisplus.extension.enums.ApiErrorCode;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.RowBounds;
import org.assertj.core.util.Maps;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@SpringBootTest
public class PaginationTests {

    @Resource
    private UserMapper userMapper;

    @Test
    void lambdaPagination() {
        Page<User> page = new Page<>(1, 3);
        IPage<User> res = userMapper.selectPage(page, Wrappers.<User>lambdaQuery().ge(User::getAge, 20).orderByAsc(User::getAge));
        assertThat(res.getTotal()).isGreaterThan(2L);
        assertThat(res.getRecords().size()).isEqualTo(2);
    }

    @Test
    public void test1() {
        log.error("----------------------------------baseMapper 自带分页-------------------------------------------------------");
        Page<User> page = new Page<>(1, 3);
        IPage<User> userIPage = userMapper.selectPage(page, new QueryWrapper<User>()
            .eq("age", 20).eq("name", "az"));
        assertThat(page).isSameAs(userIPage);
        log.error("总条数 -------------> {}", userIPage.getTotal());
        log.error("当前页数 -------------> {}", userIPage.getCurrent());
        log.error("当前每页显示数 -------------> {}", userIPage.getSize());

        List<User> records = userIPage.getRecords();
        assertThat(records).isNotEmpty();

        log.error("----------------------------------json 正反序列化-------------------------------------------------------");
        String json = JSON.toJSONString(page);
        log.info("json ----------> {}", json);
        Page<User> page1 = JSON.parseObject(json, new TypeReference<Page<User>>() {
        });
        List<User> records1 = page1.getRecords();
        assertThat(records1).isNotEmpty();
        assertThat(records1.get(0).getClass()).isEqualTo(User.class);

        log.error("----------------------------------自定义 XML 分页-------------------------------------------------------");
        MyPage<User> myPage = new MyPage<User>(1, 5).setSelectInt(20).setSelectStr("Jack");
        MyParam params = new MyParam(20, "Jack");
        MyPage<User> userMyPage = userMapper.mySelectPage(myPage, params);
        assertThat(myPage).isSameAs(userMyPage);
        log.error("总条数 -------------> {}", userMyPage.getTotal());
        log.error("当前页数 -------------> {}", userMyPage.getCurrent());
        log.error("当前每页显示数 -------------> {}", userMyPage.getSize());
    }

    @Test
    public void tests2() {
        /* 下面的 left join 不会对 count 进行优化,因为 where 条件里有 join 的表的条件 */
        MyPage<UserChildren> myPage = new MyPage<>(1, 5);
        myPage.setSelectInt(20).setSelectStr("a1");
        MyPage<UserChildren> userChildrenMyPage = userMapper.userChildrenPage(myPage);
        List<UserChildren> records = userChildrenMyPage.getRecords();
        records.forEach(System.out::println);

        /* 下面的 left join 会对 count 进行优化,因为 where 条件里没有 join 的表的条件 */
        myPage = new MyPage<UserChildren>(1, 5).setSelectInt(18);
        userChildrenMyPage = userMapper.userChildrenPage(myPage);
        records = userChildrenMyPage.getRecords();
        records.forEach(System.out::println);
    }

    private <T> void print(List<T> list) {
        if (!CollectionUtils.isEmpty(list)) {
            list.forEach(System.out::println);
        }
    }


    @Test
    public void testMyPageMap() {
        MyPage<User> myPage = new MyPage<User>(1, 5).setSelectInt(20).setSelectStr("az");
        userMapper.mySelectPageMap(myPage, Maps.newHashMap("name", "%a"));
        myPage.getRecords().forEach(System.out::println);
    }

    @Test
    public void testMap() {
        userMapper.mySelectMap(Maps.newHashMap("name", "%a")).forEach(System.out::println);
    }

    @Test
    public void myPage() {
        MyPage<User> page = new MyPage<>(1, 5);
        page.setName("az");
        userMapper.myPageSelect(page).forEach(System.out::println);
    }

    @Test
    public void iPageTest() {
        IPage<User> page = new Page<User>(1, 5) {
            private String name = "%";

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        };

        List<User> list = userMapper.iPageSelect(page);
        System.out.println("list.size=" + list.size());
        System.out.println("page.total=" + page.getTotal());
    }

    @Test
    public void rowBoundsTest() {
        RowBounds rowBounds = new RowBounds(1, 5);
        List<User> list = userMapper.rowBoundList(rowBounds, Maps.newHashMap("name", "%"));
        System.out.println("list.size=" + list.size());
        list.forEach(System.out::println);
    }

    @Test
    public void testSelect() {
        System.out.println("----- selectAll method test ------");
        List<User> users = userMapper.selectList(null);
        Assert.eq(2, users.size(), ApiErrorCode.FAILED);
        users.forEach(System.out::println);
    }
}
