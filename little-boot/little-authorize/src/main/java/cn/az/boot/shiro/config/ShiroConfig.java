// package cn.az.boot.shiro.config;

// import org.apache.shiro.cache.CacheManager;
// import org.apache.shiro.cache.MemoryConstrainedCacheManager;
// import org.apache.shiro.codec.Base64;
// import org.apache.shiro.spring.LifecycleBeanPostProcessor;
// import
// org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
// import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
// import org.apache.shiro.web.mgt.CookieRememberMeManager;
// import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
// import org.apache.shiro.web.servlet.SimpleCookie;
// import
// org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.context.annotation.DependsOn;

// import java.util.LinkedHashMap;

// /**
// * <h3>MySpringBoot</h3>
// *
// * @author : azchino
// **/
// @Configuration
// public class ShiroConfig {

// @Bean
// public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager
// securityManager) {
// ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
// shiroFilterFactoryBean.setSecurityManager(securityManager);
// shiroFilterFactoryBean.setLoginUrl("/login");
// shiroFilterFactoryBean.setSuccessUrl("/index");
// shiroFilterFactoryBean.setUnauthorizedUrl("/403");
// LinkedHashMap<String, String> filterChainDefinitionMap = new
// LinkedHashMap<>();

// filterChainDefinitionMap.put("/css/**", "anon");
// filterChainDefinitionMap.put("/js/**", "anon");
// filterChainDefinitionMap.put("/fonts/**", "anon");
// filterChainDefinitionMap.put("/img/**", "anon");
// filterChainDefinitionMap.put("/druid/**", "anon");
// filterChainDefinitionMap.put("/logout", "logout");
// filterChainDefinitionMap.put("/", "anon");
// filterChainDefinitionMap.put("/**", "user");

// shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

// return shiroFilterFactoryBean;
// }

// @Bean
// public SecurityManager securityManager() {
// DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
// securityManager.setRealm(shiroRealm());
// securityManager.setRememberMeManager(rememberMeManager());
// securityManager.setCacheManager(cacheManager());
// return securityManager;
// }

// @Bean
// public ShiroRealm shiroRealm() {
// return new ShiroRealm();
// }

// @Bean
// public AuthorizationAttributeSourceAdvisor
// authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
// AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new
// AuthorizationAttributeSourceAdvisor();
// authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
// return authorizationAttributeSourceAdvisor;
// }

// @Bean(name = "lifecycleBeanPostProcessor")
// public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
// return new LifecycleBeanPostProcessor();
// }

// @Bean
// @DependsOn({"lifecycleBeanPostProcessor"})
// public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator() {
// DefaultAdvisorAutoProxyCreator creator = new
// DefaultAdvisorAutoProxyCreator();
// creator.setProxyTargetClass(true);
// return creator;
// }

// public SimpleCookie rememberMeCookie() {
// SimpleCookie cookie = new SimpleCookie("rememberMe");
// cookie.setMaxAge(86400);
// return cookie;
// }

// public CookieRememberMeManager rememberMeManager() {
// CookieRememberMeManager cookieRememberMeManager = new
// CookieRememberMeManager();
// cookieRememberMeManager.setCookie(rememberMeCookie());
// cookieRememberMeManager.setCipherKey(Base64.decode("4AvVhmFLUs0KTA3Kprsdag=="));
// return cookieRememberMeManager;
// }

// @Bean
// public CacheManager cacheManager() {
// return new MemoryConstrainedCacheManager();
// }
// }
