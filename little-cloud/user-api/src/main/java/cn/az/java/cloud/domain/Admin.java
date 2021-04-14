package cn.az.java.cloud.domain;

import java.io.Serializable;

/**
 * @author az
 * @since 2020-04-15
 */
public class Admin implements Serializable {

    private static final long serialVersionUID = -1915833094239717623L;

    private Long id;
    private String name;

    public Admin() {
    }

    public Admin(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return "Admin{" +
            "id=" + id +
            ", name='" + name + '\'' +
            '}';
    }
}
