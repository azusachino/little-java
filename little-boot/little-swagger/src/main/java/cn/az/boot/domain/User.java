package cn.az.boot.domain;

import lombok.Data;

/**
 * @author azusachino
 * @version 12/12/2019
 */
@Data
public class User {

    private static final long serialVersionUID = -273159832720972274L;

    private Long id;
    private String name;
    private Integer age;
}
