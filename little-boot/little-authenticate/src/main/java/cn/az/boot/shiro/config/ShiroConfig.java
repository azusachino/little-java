package cn.az.boot.shiro.config;

import java.util.LinkedHashMap;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <h3>MySpringBoot</h3>
 *
 * @author : azchino
 **/
@Configuration
public class ShiroConfig {

    // @Bean
    // public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
    //     ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
    //     bean.setSecurityManager(securityManager);
    //     bean.setLoginUrl("/login");
    //     bean.setSuccessUrl("/index");
    //     bean.setUnauthorizedUrl("/403");

    //     LinkedHashMap<String, String> map = new LinkedHashMap<>();
    //     map.put("/css/**", "anon");
    //     map.put("/js/**", "anon");
    //     map.put("/fonts/**", "anon");
    //     map.put("/img/**", "anon");
    //     map.put("/druid/**", "anon");
    //     map.put("/logout", "logout");
    //     map.put("/", "anon");
    //     map.put("/**", "authc");

    //     bean.setFilterChainDefinitionMap(map);

    //     return bean;
    // }

    // @Bean
    // public SecurityManager securityManager() {
    //     DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
    //     securityManager.setRealm(shiroRealm());
    //     return securityManager;
    // }

    // @Bean
    // public ShiroRealm shiroRealm() {
    //     return new ShiroRealm();
    // }
}
