package cn.az.java.spring.scope;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;
import org.springframework.core.NamedThreadLocal;

/**
 * @author az
 * @since 07/30/20
 */
public class LittleScope implements Scope {

    public static final String SCOPE_NAME = "LittleScope";

    public final NamedThreadLocal<Map<String, Object>> DATA = new NamedThreadLocal<Map<String, Object>>(SCOPE_NAME) {
        @Override
        protected Map<String, Object> initialValue() {
            return new HashMap<>();
        }
    };

    @Override
    public Object get(String name, ObjectFactory<?> objectFactory) {
        Map<String, Object> map = getContext();
        Object o = map.get(name);
        if (Objects.isNull(o)) {
            o = objectFactory.getObject();
            map.put(name, o);
        }
        return o;
    }

    @Override
    public Object remove(String name) {
        return null;
    }

    @Override
    public void registerDestructionCallback(String name, Runnable callback) {

    }

    @Override
    public Object resolveContextualObject(String key) {
        return null;
    }

    @Override
    public String getConversationId() {
        return null;
    }

    private Map<String, Object> getContext() {
        return DATA.get();
    }
}
