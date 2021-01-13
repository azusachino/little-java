package cn.az.boot.test.condition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.util.Map;

/**
 * @author az
 */
public class OnSystemPropertyCondition implements Condition {

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        Map<String, Object> data = metadata.getAnnotationAttributes(ConditionalOnSystemProperty.class.getName());
        String propertyName = String.valueOf(data.get("name"));
        String propertyValue = String.valueOf(data.get("value"));

        String javaPropertyValue = System.getProperty(propertyName);
        return javaPropertyValue.endsWith(propertyValue);
    }
}
