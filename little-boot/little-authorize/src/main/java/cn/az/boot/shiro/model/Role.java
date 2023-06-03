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
public class Role implements Serializable {
    private static final long serialVersionUID = -227437593919820521L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String memo;

    @ManyToMany
    @JoinTable(name = "rolePermission", joinColumns = {@JoinColumn(name = "rid")}, inverseJoinColumns = {@JoinColumn(name = "pid")})
    private List<Permission> permissions;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "userRole", joinColumns = {@JoinColumn(name = "rid")}, inverseJoinColumns = {@JoinColumn(name = "uid")})
    private List<User> users;

}
