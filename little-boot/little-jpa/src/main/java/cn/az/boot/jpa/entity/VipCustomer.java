package cn.az.boot.jpa.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;

/**
 * @author az
 */
@Data
@Entity
@Inheritance
@EqualsAndHashCode(callSuper = true)
public class VipCustomer extends Customer {

    private String privilege;
}
