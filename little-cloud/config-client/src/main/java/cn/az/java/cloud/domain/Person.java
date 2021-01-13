package cn.az.java.cloud.domain;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Person
 *
 * @author <a href="mailto:azusa146@gmail.com">az</a>
 * @see domain
 * @since 2020-03-19
 */
@Data
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "person")
public class Person {

    private Long id;
    private String name;
}
