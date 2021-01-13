package cn.az.boot.test;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.env.MockEnvironment;

import java.util.Objects;

/**
 * @author az
 */
@SpringBootTest
public class EnvironmentTest {

    @Test
    public void testSystemProperty() {
        assert Objects.nonNull(System.getProperty("os.arch"));

        MockEnvironment environment = new MockEnvironment();
        environment.setProperty("user.country", "EN");

        assert "EN".equals(environment.getProperty("user.country"));
    }
}
