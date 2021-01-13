package cn.az.java.cloud.domain;

import org.springframework.http.HttpStatus;

import java.util.HashMap;

/**
 * The type Rest response.
 *
 * @author : azusachino
 * @date : 2019/9/15
 */
public class RestResponse extends HashMap<String, Object> {

    private static final long serialVersionUID = 6749600779320205323L;

    /**
     * Ok rest response.
     *
     * @return the rest response
     */
    public RestResponse ok() {
        this.put("code", 200);
        return this;
    }

    public RestResponse code(HttpStatus status) {
        this.put("code", status.value());
        return this;
    }

    /**
     * Message object.
     *
     * @param message the message
     * @return the object
     */
    public RestResponse message(String message) {
        this.put("message", message);
        return this;
    }

    /**
     * Message object.
     *
     * @param data the data
     * @return the object
     */
    public RestResponse data(Object data) {
        this.put("data", data);
        return this;
    }

    @Override
    public RestResponse put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
