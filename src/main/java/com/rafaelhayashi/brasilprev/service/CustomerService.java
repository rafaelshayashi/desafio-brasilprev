package com.rafaelhayashi.brasilprev.service;

import com.rafaelhayashi.brasilprev.controller.CustomerForm;
import com.rafaelhayashi.brasilprev.model.Customer;
import com.rafaelhayashi.brasilprev.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    public Page<Customer> list(Pageable pageable) {
        return this.customerRepository.findAll(pageable);
    }

    public Customer create(CustomerForm customerForm) {
        Customer customer = customerForm.convert();
        this.customerRepository.save(customer);
        return customer;
    }
}
