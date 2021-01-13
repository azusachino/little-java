package cn.az.boot.servlet;

import cn.az.boot.servlet.servlet.AsyncServlet;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.filter.CharacterEncodingFilter;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import java.util.EnumSet;

/**
 * @author az
 * @since 2020-04-01
 */
@SpringBootApplication
public class LittleServletApplication {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplicationBuilder(LittleServletApplication.class)
                .web(WebApplicationType.SERVLET)
                .bannerMode(Banner.Mode.OFF)
                .build();
        application.run(args);
    }

    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public ServletRegistrationBean<?> asyncServletServletRegistrationBean() {
        ServletRegistrationBean<?> registrationBean = new ServletRegistrationBean<>(new AsyncServlet(), "/");
        registrationBean.setName("MyAsyncServlet");
        return registrationBean;
    }

    @Bean
    public ServletContextInitializer servletContextInitializer() {
        return servletContext -> {
            CharacterEncodingFilter filter = new CharacterEncodingFilter();
            FilterRegistration.Dynamic registration = servletContext.addFilter("filter", filter);
            servletContext.addServlet("myServlet", AsyncServlet.class);
            registration.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), false, "/");
        };
    }
}
