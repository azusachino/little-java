package cn.az.boot.jpa.entity;

import cn.az.boot.jpa.entity.listener.CustomerListener;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * @author az
 */
@Data
@Entity
@Access(AccessType.FIELD)
@EntityListeners(value = CustomerListener.class)
public class Customer {

    @Id
    @GeneratedValue
    private Long id;
    @Column(length = 60)
    private String name;

    @OneToOne
    private CreditCard creditCard;

    @ManyToOne
    private Store store;

    @ManyToMany
    private List<Book> books;

}
