package cn.az.boot.shiro.model;

import lombok.Data;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * <h3>MySpringBoot</h3>
 *
 * @author : azchino
 **/
@Data
@Entity
public class Permission implements Serializable {
    private static final long serialVersionUID = 7160557680614732403L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String url;
    private String name;

    @ManyToMany
    @JoinTable(name = "rolePermission", joinColumns = {@JoinColumn(name = "pid")}, inverseJoinColumns = {@JoinColumn(name = "rid")})
    private List<Role> roles;
}
