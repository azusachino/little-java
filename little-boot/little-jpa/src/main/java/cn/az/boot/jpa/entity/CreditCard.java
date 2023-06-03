package cn.az.boot.jpa.entity;

import lombok.Data;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * @author az
 */
@Data
@Entity
@Table(name = "credit_card")
@Access(value = AccessType.FIELD)
public class CreditCard {

    @Id
    @GeneratedValue
    private Long id;
    @Column(length = 128)
    private String number;
    @Column(name = "reg_date")
    private LocalDateTime registerDateTime;

    @OneToOne(mappedBy = "creditCard")
    private Customer customer;
}
