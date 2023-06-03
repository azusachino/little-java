package cn.az.boot.rest.method.support;

import cn.az.boot.rest.http.converter.properties.PropertiesHttpMessageConverter;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import jakarta.servlet.http.HttpServletRequest;
import java.util.Properties;

/**
 * {@link Properties 类型} {@link HandlerMethodArgumentResolver}
 *
 * @author az
 */
public class PropertiesHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return Properties.class.equals(parameter.getParameterType());
    }

    @Override
    public Object resolveArgument(@NonNull MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  @NonNull NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

        // 复用 PropertiesHttpMessageConverter
        PropertiesHttpMessageConverter converter = new PropertiesHttpMessageConverter();

        ServletWebRequest servletWebRequest = (ServletWebRequest) webRequest;
        // Servlet Request API
        HttpServletRequest request = servletWebRequest.getRequest();

        HttpInputMessage httpInputMessage = new ServletServerHttpRequest(request);

        return converter.read(null, null, httpInputMessage);
    }
}
