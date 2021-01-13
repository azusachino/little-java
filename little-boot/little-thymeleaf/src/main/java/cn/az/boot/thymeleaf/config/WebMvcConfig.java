package cn.az.boot.thymeleaf.config;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import java.io.File;

/**
 * @author az
 * @since 2020-03-30
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.favorParameter(true);
    }

    @Bean
    public ViewResolver myViewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/jsp/");
        viewResolver.setSuffix(".jsp");
        // ThymeleafViewResolver Ordered.LOWEST_PRECEDENCE
        viewResolver.setOrder(Ordered.LOWEST_PRECEDENCE - 5);
        // 优先级高于 ThymeleafViewResolver
        // 配置 ViewResolver 的 Content-Type
        viewResolver.setContentType("text/xml;charset=UTF-8");
        return viewResolver;
    }

    @Bean
    public WebServerFactoryCustomizer<TomcatServletWebServerFactory> customizer() {
        return factory -> factory.addContextCustomizers(connector -> {
                    String relativePath = "little-thymeleaf/src/main/webapp";
                    // 相对于 user.dir = E:\projects\project-github\little-boot
                    File docBaseFile = new File(relativePath);
                    if (docBaseFile.exists()) { // 路径是否存在
                        // 解决 Maven 多模块 JSP 无法读取的问题
                        connector.setDocBase(docBaseFile.getAbsolutePath());
                    }
                }
        );
    }
}
