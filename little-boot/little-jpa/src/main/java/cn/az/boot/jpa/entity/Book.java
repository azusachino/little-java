package cn.az.boot.jpa.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

/**
 * @author az
 */
@Data
@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String isbn;
    private LocalDate publishDate;

    @ManyToOne
    private Store store;

    @ManyToMany(mappedBy = "books")
    private List<Customer> customers;
}
