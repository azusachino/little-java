package cn.az.boot.jpa.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * @author az
 */
@Data
@Entity
@Table(name = "stores")
public class Store {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @OneToMany(mappedBy = "store")
    private List<Customer> customers;
}
