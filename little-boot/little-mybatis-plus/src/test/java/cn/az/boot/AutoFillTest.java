package cn.az.boot;

import cn.az.boot.entity.User;
import cn.az.boot.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @author azusachino
 * @version 12/13/2019
 */
@Slf4j
@SpringBootTest
public class AutoFillTest {

    @Resource
    private UserMapper userMapper;

    @Test
    public void test() {
        User user = new User(null, "Tom", 1, "tom@qq.com", null, null);
        userMapper.insert(user);
        log.info("query user:{}", userMapper.selectById(user.getId()));
        User beforeUser = userMapper.selectById(1L);
        log.info("before user:{}", beforeUser);
        beforeUser.setAge(12);
        userMapper.updateById(beforeUser);
        log.info("query user:{}", userMapper.selectById(1L));
    }
}
