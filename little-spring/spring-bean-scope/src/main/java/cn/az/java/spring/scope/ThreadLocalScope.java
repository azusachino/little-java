package cn.az.java.spring.scope;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;
import org.springframework.core.NamedThreadLocal;
import org.springframework.lang.NonNull;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author az
 * @since 07/30/20
 */
public class ThreadLocalScope implements Scope {

    public static final String THREAD_LOCAL_SCOPE = "threadLocalScope";

    private final NamedThreadLocal<Map<String, Object>> namedThreadLocal = new NamedThreadLocal<Map<String, Object>>(THREAD_LOCAL_SCOPE) {
        @Override
        protected Map<String, Object> initialValue() {
            return new HashMap<>(16);
        }
    };

    private final ThreadLocal<Map<String, Object>> threadLocal = ThreadLocal.withInitial(HashMap::new);

    @Override
    @NonNull
    public Object get(@NonNull String name, @NonNull ObjectFactory<?> objectFactory) {
        Map<String, Object> context = getContext();
        Object obj = context.get(name);
        if (Objects.isNull(obj)) {
            obj = objectFactory.getObject();
            context.put(name, obj);
        }
        return obj;
    }

    @Override
    public Object remove(@NonNull String name) {
        return getContext().remove(name);
    }

    @Override
    public void registerDestructionCallback(@NonNull String name, @NonNull Runnable callback) {

    }

    @Override
    public Object resolveContextualObject(@NonNull String key) {
        return getContext().get(key);
    }

    @Override
    public String getConversationId() {
        return String.valueOf(Thread.currentThread().getId());
    }

    @NonNull
    private Map<String, Object> getContext() {
        threadLocal.get();
        return namedThreadLocal.get();
    }
}
