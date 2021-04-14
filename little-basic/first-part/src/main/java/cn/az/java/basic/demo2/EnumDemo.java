package cn.az.java.basic.demo2;

import java.lang.reflect.Modifier;
import java.util.stream.Stream;

/**
 * @author <a href="mailto:azusa146@gmail.com">az</a>
 * @see Enum
 * @since 2020-03-19
 */

public final class EnumDemo {

    private final EnumDemo e1 = new EnumDemo(1, 2);
    private final EnumDemo e2 = new EnumDemo(2, 3);

    private Integer a;
    private Integer b;

    public EnumDemo(Integer a, Integer b) {
        this.a = a;
        this.b = b;
    }

    public EnumDemo[] values() {
        return Stream.of(EnumDemo.class.getDeclaredFields())
            .filter(field -> {
                int modifiers = field.getModifiers();
                return Modifier.isPublic(modifiers) && Modifier.isStatic(modifiers) && Modifier.isFinal(modifiers);
            }).map(field -> {
                // Field -> Counting
                try {
                    return (EnumDemo) field.get(null);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }).toArray(EnumDemo[]::new);
    }
}
