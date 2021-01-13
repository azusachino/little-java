package cn.az.boot.jpa.repository;

import cn.az.boot.jpa.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

/**
 * @author az
 */
@Repository
@Transactional
public class CustomerRepository extends SimpleJpaRepository<Customer, Long> {


    /**
     * Creates a new {@link SimpleJpaRepository} to manage objects of the given domain type.
     *
     * @param domainClass must not be {@literal null}.
     * @param em          must not be {@literal null}.
     */
    @Autowired
    public CustomerRepository(EntityManager em) {
        super(Customer.class, em);
    }
}
