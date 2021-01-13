package cn.az.java.cloud.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author azusachino
 * @version 2019/12/08
 */
@Data
@AllArgsConstructor
public class User {

    private Long id;
    private String username;
    private String password;
}
