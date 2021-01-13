package cn.az.boot.test.autoconfigure;

import cn.az.boot.test.annotation.EnableHelloWorld;
import cn.az.boot.test.condition.ConditionalOnSystemProperty;
import org.springframework.context.annotation.Configuration;

/**
 * @author az
 */
@Configuration
@EnableHelloWorld
@ConditionalOnSystemProperty(name = "user.name", value = "azusachino")
public class HelloWorldAutoConfiguration {
}
