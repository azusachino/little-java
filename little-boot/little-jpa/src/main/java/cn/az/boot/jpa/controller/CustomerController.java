package cn.az.boot.jpa.controller;

import cn.az.boot.jpa.entity.Customer;
import cn.az.boot.jpa.repository.CustomerRepository;
import cn.az.boot.jpa.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author az
 */
@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("/customer/{id}")
    public Customer customer(@PathVariable("id") Long id) {
        return customerService.getCustomerById(id);
    }

    @GetMapping("/customers")
    public List<Customer> customers() {
        return customerRepository.findAll();
    }
}
