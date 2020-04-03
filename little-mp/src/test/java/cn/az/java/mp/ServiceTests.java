package cn.az.java.mp;

import cn.az.java.mp.service.IUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author az
 * @since 2020-04-03
 */
@SpringBootTest(classes = LittleMpApplication.class)
public class ServiceTests {

    private IUserService userService;

    @Autowired
    public void setUserService(IUserService userService) {
        this.userService = userService;
    }

    @Test
    public void t1() {
        System.out.println(userService.list());
    }
}
