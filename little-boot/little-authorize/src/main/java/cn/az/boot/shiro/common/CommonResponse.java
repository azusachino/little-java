package cn.az.boot.shiro.common;

import java.util.HashMap;
import java.util.Map;

/**
 * <h3>MySpringBoot</h3>
 *
 * @author : azchino
 * @date : 2019-07-20 10:22
 **/
public class CommonResponse extends HashMap<String, Object> {

    private static final long serialVersionUID = -1L;

    public CommonResponse() {
        put("code", 200);
        put("message", "operation success");
    }

    public static CommonResponse error() {
        return error(1, "operation failed");
    }

    public static CommonResponse error(String message) {
        return error(500, message);
    }

    public static CommonResponse error(int code, String message) {
        CommonResponse commonResponse = new CommonResponse();
        commonResponse.put("code", code);
        commonResponse.put("message", message);
        return commonResponse;
    }

    public static CommonResponse ok(Map<String, Object> map) {
        CommonResponse response = new CommonResponse();
        response.putAll(map);
        return response;
    }

    public static CommonResponse ok(String message) {
        CommonResponse response = new CommonResponse();
        response.put("message", message);
        return response;
    }

    public static CommonResponse ok() {
        return new CommonResponse();
    }

    @Override
    public CommonResponse put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
