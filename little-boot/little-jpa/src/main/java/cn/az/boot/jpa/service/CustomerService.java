package cn.az.boot.jpa.service;

import cn.az.boot.jpa.entity.Customer;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

/**
 * @author az
 */
@Service
public class CustomerService {

    @PersistenceContext
    private EntityManager entityManager;

    public void addCustomer(Customer customer) {
        entityManager.persist(customer);
    }

    public Customer getCustomerById(Long id) {
        return entityManager.find(Customer.class, id);
    }
}
