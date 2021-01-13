package cn.az.boot.shiro.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * <h3>MySpringBoot</h3>
 *
 * @author : azchino
 * @date : 2019-07-20 17:30
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
