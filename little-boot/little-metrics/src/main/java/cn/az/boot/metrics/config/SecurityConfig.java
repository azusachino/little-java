package cn.az.boot.metrics.config;

import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * @author ycpang
 * @since 2021-03-29 19:31
 */
@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // any request match endpoint check role
        http.securityMatcher(EndpointRequest.toAnyEndpoint())
                .authorizeRequests(r -> r.anyRequest()
                        .hasRole("ENDPOINT_ADMIN"));
        http.httpBasic();
        return http.build();
    }
}
