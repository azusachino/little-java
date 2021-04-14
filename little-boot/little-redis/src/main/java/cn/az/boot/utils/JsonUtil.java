package cn.az.boot.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Optional;

/**
 * @author az
 */
public class JsonUtil {

    private JsonUtil() {
    }

    private static final ObjectMapper OM = new ObjectMapper();

    public static <T> Optional<String> toString(T t) {
        try {
            return Optional.of(OM.writeValueAsString(t));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public static <T> Optional<T> toBean(String json, Class<T> clazz) {
        try {
            return Optional.of(OM.readValue(json, clazz));
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
