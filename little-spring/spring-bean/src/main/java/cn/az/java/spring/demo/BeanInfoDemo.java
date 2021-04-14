package cn.az.java.spring.demo;

import cn.az.java.spring.bean.Person;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyEditorSupport;
import java.util.stream.Stream;

/**
 * BeanInfoDemo
 *
 * @author <a href="mailto:azusa146@gmail.com">az</a>
 * @see BeanInfo
 * @since 2020-03-23
 */
@Slf4j
public class BeanInfoDemo {

    @SneakyThrows
    public static void main(String[] args) {
        BeanInfo beanInfo = Introspector.getBeanInfo(Person.class, Object.class);

        Stream.of(beanInfo.getPropertyDescriptors())
            .forEach(p -> {
                String propertyName = p.getName();
                if ("id".equals(propertyName)) {
                    p.setPropertyEditorClass(StringToIntegerPropertyEditor.class);
                }
                log.error(p.toString());
            });

    }

    static class StringToIntegerPropertyEditor extends PropertyEditorSupport {
        /**
         * Sets the property value by parsing a given String.  May raise
         * java.lang.IllegalArgumentException if either the String is
         * badly formatted or if this kind of property can't be expressed
         * as text.
         *
         * @param text The string to be parsed.
         */
        @Override
        public void setAsText(String text) throws IllegalArgumentException {
            Integer v = Integer.valueOf(text);
            super.setAsText(text);
        }
    }
}
