package cn.az.boot.cache.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author az
 */
@Data
public class Person implements Serializable {

    private static final long serialVersionUID = -8890380559223050044L;

    private String id;

    private String name;
    private Integer age;
}
