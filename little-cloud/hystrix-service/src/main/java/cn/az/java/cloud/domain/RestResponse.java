package cn.az.java.cloud.domain;

import org.springframework.http.HttpStatus;

import java.util.HashMap;

/**
 * The type Rest response.
 *
 * @author : azusachino
 */
public class RestResponse extends HashMap<String, Object> {

    private static final long serialVersionUID = 6749600779320205323L;

    /**
     * Ok rest response.
     *
     * @return the rest response
     */
    public RestResponse ok() {
        return this.put("code", 200);
    }

    /**
     * Code rest response.
     *
     * @param status the status
     * @return the rest response
     */
    public RestResponse code(HttpStatus status) {
        return this.put("code", status.value());
    }

    /**
     * Code object.
     *
     * @return the object
     */
    public Object code() {
        return this.getOrDefault("code", "no code for you");
    }

    /**
     * msg object.
     *
     * @param msg the msg
     * @return the object
     */
    public RestResponse msg(String msg) {
        return this.put("msg", msg);
    }

    /**
     * Msg object.
     *
     * @return the object
     */
    public Object msg() {
        return this.getOrDefault("msg", "no msg for you");
    }

    /**
     * msg object.
     *
     * @param data the data
     * @return the object
     */
    public RestResponse data(Object data) {
        return this.put("data", data);
    }

    /**
     * msg object.
     *
     * @param data the data
     * @return the object
     */
    public Object data() {
        return this.getOrDefault("data", "no data for you");
    }

    @Override
    public RestResponse put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
