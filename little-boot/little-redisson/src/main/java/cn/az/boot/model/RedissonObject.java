package cn.az.boot.model;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author Liz
 */
@Data
@ToString
public class RedissonObject implements Serializable {

    private static final long serialVersionUID = 126822182542318006L;
    private Long id;
    private String content;
}
