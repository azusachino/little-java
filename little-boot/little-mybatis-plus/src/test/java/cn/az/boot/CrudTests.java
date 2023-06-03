package cn.az.boot;

import cn.az.boot.entity.User;
import cn.az.boot.mapper.UserMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import jakarta.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author azusachino
 * @version 12/13/2019
 */
@SpringBootTest
public class CrudTests {

    @Resource
    private UserMapper mapper;

    @Test
    public void aInsert() {
        User user = new User();
        user.setName("小羊");
        user.setAge(3);
        user.setEmail("abc@mp.com");
        Assertions.assertThat(mapper.insert(user)).isGreaterThan(0);
        // 成功直接拿会写的 ID
        Assertions.assertThat(user.getId()).isNotNull();
    }


    @Test
    public void bDelete() {
        Assertions.assertThat(mapper.deleteById(3L)).isGreaterThan(0);
        Assertions.assertThat(mapper.delete(new QueryWrapper<User>()
            .lambda().eq(User::getName, "qz"))).isGreaterThan(0);
    }


    @Test
    public void cUpdate() {
        Assertions.assertThat(mapper.updateById(new User().setId(1L).setEmail("ab@c.c"))).isGreaterThan(0);
        Assertions.assertThat(
            mapper.update(
                new User().setName("mp"),
                Wrappers.<User>lambdaUpdate()
                    .set(User::getAge, 3)
                    .eq(User::getId, 2)
            )
        ).isGreaterThan(0);
        User user = mapper.selectById(2);
        Assertions.assertThat(user.getAge()).isEqualTo(3);
        Assertions.assertThat(user.getName()).isEqualTo("mp");

        mapper.update(
            null,
            Wrappers.<User>lambdaUpdate().set(User::getEmail, null).eq(User::getId, 2)
        );
        Assertions.assertThat(mapper.selectById(1).getEmail()).isEqualTo("ab@c.c");
        user = mapper.selectById(2);
        Assertions.assertThat(user.getEmail()).isNull();
        Assertions.assertThat(user.getName()).isEqualTo("mp");

        mapper.update(
            new User().setEmail("miemie@baomidou.com"),
            new QueryWrapper<User>()
                .lambda().eq(User::getId, 2)
        );
        user = mapper.selectById(2);
        Assertions.assertThat(user.getEmail()).isEqualTo("miemie@baomidou.com");

        mapper.update(
            new User().setEmail("miemie2@baomidou.com"),
            Wrappers.<User>lambdaUpdate()
                .set(User::getAge, null)
                .eq(User::getId, 2)
        );
        user = mapper.selectById(2);
        Assertions.assertThat(user.getEmail()).isEqualTo("miemie2@baomidou.com");
        Assertions.assertThat(user.getAge()).isNull();
    }


    @Test
    public void dSelect() {
        mapper.insert(
            new User().setId(10086L)
                .setName("miemie")
                .setEmail("miemie@baomidou.com")
                .setAge(3));
        Assertions.assertThat(mapper.selectById(10086L).getEmail()).isEqualTo("miemie@baomidou.com");
        User user = mapper.selectOne(new QueryWrapper<User>().lambda().eq(User::getId, 10086));
        Assertions.assertThat(user.getName()).isEqualTo("miemie");
        Assertions.assertThat(user.getAge()).isEqualTo(3);

        mapper.selectList(Wrappers.<User>lambdaQuery().select(User::getId))
            .forEach(x -> {
                Assertions.assertThat(x.getId()).isNotNull();
                Assertions.assertThat(x.getEmail()).isNull();
                Assertions.assertThat(x.getName()).isNull();
                Assertions.assertThat(x.getAge()).isNull();
            });
        mapper.selectList(new QueryWrapper<User>().select("id", "name"))
            .forEach(x -> {
                Assertions.assertThat(x.getId()).isNotNull();
                Assertions.assertThat(x.getEmail()).isNull();
                Assertions.assertThat(x.getName()).isNotNull();
                Assertions.assertThat(x.getAge()).isNull();
            });
    }

    @Test
    public void orderBy() {
        List<User> users = mapper.selectList(Wrappers.<User>query().orderByAsc("age"));
        Assertions.assertThat(users).isNotEmpty();
    }

    @Test
    public void selectMaps() {
        List<Map<String, Object>> mapList = mapper.selectMaps(Wrappers.<User>query().orderByAsc("age"));
        Assertions.assertThat(mapList).isNotEmpty();
        Assertions.assertThat(mapList.get(0)).isNotEmpty();
        System.out.println(mapList.get(0));
    }

    @Test
    public void selectMapsPage() {
        IPage<Map<String, Object>> page = mapper.selectMapsPage(new Page<>(1, 5), Wrappers.<User>query().orderByAsc("age"));
        Assertions.assertThat(page).isNotNull();
        Assertions.assertThat(page.getRecords()).isNotEmpty();
        Assertions.assertThat(page.getRecords().get(0)).isNotEmpty();
        System.out.println(page.getRecords().get(0));
    }

    @Test
    public void orderByLambda() {
        List<User> users = mapper.selectList(Wrappers.<User>lambdaQuery().orderByAsc(User::getAge));
        Assertions.assertThat(users).isNotEmpty();
    }

    @Test
    public void testSelectMaxId() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.select("max(id) as id");
        User user = mapper.selectOne(wrapper);
        System.out.println("maxId=" + user.getId());
        List<User> users = mapper.selectList(Wrappers.<User>lambdaQuery().orderByDesc(User::getId));
        Assert.assertEquals(user.getId().longValue(), users.get(0).getId().longValue());
    }

    @Test
    public void testGroup() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.select("age, count(*)")
            .groupBy("age");
        List<Map<String, Object>> maplist = mapper.selectMaps(wrapper);
        for (Map<String, Object> mp : maplist) {
            System.out.println(mp);
        }
        LambdaQueryWrapper<User> lambdaQueryWrapper = new QueryWrapper<User>().lambda()
            .select(User::getAge)
            .groupBy(User::getAge)
            .orderByAsc(User::getAge);
        for (User user : mapper.selectList(lambdaQueryWrapper)) {
            System.out.println(user);
        }
    }

    @Test
    public void testTableFieldExistFalse() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.select("age, count(age) as count")
            .groupBy("age");
        List<User> list = mapper.selectList(wrapper);
        list.forEach(System.out::println);
        list.forEach(x -> {
            Assert.assertNull(x.getId());
            Assert.assertNotNull(x.getAge());
            Assert.assertNotNull(x.getCount());
        });
        mapper.insert(
            new User().setId(10088L)
                .setName("miemie")
                .setEmail("miemie@baomidou.com")
                .setAge(3));
        User miemie = mapper.selectById(10088L);
        Assert.assertNotNull(miemie);

    }

}
