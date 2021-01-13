package cn.az.boot.mybatis.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

/**
 * @author az
 * @date 2020-03-09
 */
@Data
@ToString
@AllArgsConstructor
public class User {

    private Integer id;
    private String name;
    private Integer age;
    private String email;
    private String operator;

}
