package cn.az.boot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author azusachino
 * @version 12/11/2019
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(@NonNull CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedHeaders("*")
                .allowedOrigins("*")
                .allowedMethods("*")
                .maxAge(3600L);
    }

    /**
     * Configure simple automated controllers pre-configured with the response
     * status code and/or a view to render the response body. This is useful in
     * cases where there is no need for custom controller logic -- e.g. render a
     * home page, perform simple site URL redirects, return a 404 status with
     * HTML content, a 204 with no content, and more.
     *
     * @param registry {@link ViewControllerRegistry}
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // 通过修改registry添加controller层
        registry.addViewController("/hello")
                .setStatusCode(HttpStatus.OK)
                .setViewName("hello.html");
    }

    /**
     * Configure exception resolvers.
     * <p>The given list starts out empty. If it is left empty, the framework
     * configures a default set of resolvers, see
     * .
     * Or if any exception resolvers are added to the list, then the application
     * effectively takes over and must provide, fully initialized, exception
     * resolvers.
     * <p>Alternatively you can use
     * {@link #extendHandlerExceptionResolvers(List)} which allows you to extend
     * or modify the list of exception resolvers configured by default.
     *
     * @param resolvers initially an empty list
     * @see #extendHandlerExceptionResolvers(List)
     */
    @Override
    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> resolvers) {
        resolvers.add((request, response, handler, ex) -> {
            ModelAndView mav = new ModelAndView();
            try {
                mav.addObject("ex", ex.getMessage());
                response.sendError(HttpServletResponse.SC_SEE_OTHER);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return mav;
        });
    }

    /**
     * Configure asynchronous request handling options.
     *
     * @param configurer {@link AsyncSupportConfigurer}
     */
    @Override
    public void configureAsyncSupport(@NonNull AsyncSupportConfigurer configurer) {
        // configurer.setTaskExecutor(threadPoolTaskExecutor);
    }

}
