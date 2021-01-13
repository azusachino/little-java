package cn.az.java.cloud.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;
import java.util.UUID;

/**
 * @author Liz
 * @date 1/8/2020
 */
@Data
public class User {

    private String id = UUID.randomUUID().toString();

    private String username;
    private String password;
    private List<GrantedAuthority> auth;

    public User(String name, String pw, List<GrantedAuthority> o) {

        username = name;
        password = pw;
        auth = o;
    }

    private static final User USER = new User("az", "123", null);

    public static User getInstance() {
        return USER;
    }
}
