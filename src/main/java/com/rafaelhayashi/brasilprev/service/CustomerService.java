package com.rafaelhayashi.brasilprev.service;

import com.rafaelhayashi.brasilprev.controller.CustomerAddressForm;
import com.rafaelhayashi.brasilprev.controller.CustomerForm;
import com.rafaelhayashi.brasilprev.controller.CustomerUpdateForm;
import com.rafaelhayashi.brasilprev.model.Address;
import com.rafaelhayashi.brasilprev.model.Customer;
import com.rafaelhayashi.brasilprev.repository.AddressRepository;
import com.rafaelhayashi.brasilprev.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final AddressRepository addressRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, AddressRepository addressRepository) {
        this.customerRepository = customerRepository;
        this.addressRepository = addressRepository;
    }


    public Page<Customer> list(Pageable pageable) {
        return this.customerRepository.findAll(pageable);
    }

    public Customer create(CustomerForm customerForm) {
        Customer customer = customerForm.convert();
        this.customerRepository.save(customer);
        return customer;
    }

    public Optional<Customer> createAddress(String uuid, CustomerAddressForm form) {
        Optional<Customer> customerOptional = this.customerRepository.findByUuid(UUID.fromString(uuid));
        if (customerOptional.isPresent()) {
            Address address = form.convert();
            this.addressRepository.save(address);
            customerOptional.get().setAddress(address);
            return customerOptional;
        }
        return Optional.empty();
    }

    public Optional<Customer> details(String uuid) {
        return this.customerRepository.findByUuid(UUID.fromString(uuid));
    }

    public boolean delete(String uuid) {
        Optional<Customer> customerOptional = customerRepository.findByUuid(UUID.fromString(uuid));
        if (customerOptional.isPresent()) {
            this.customerRepository.delete(customerOptional.get());
            return true;
        }
        return false;
    }

    public Optional<Customer> update(String uuid, CustomerUpdateForm form) {
        Optional<Customer> customerOptional = this.customerRepository.findByUuid(UUID.fromString(uuid));
        if (!customerOptional.isPresent()) {
            return Optional.empty();
        }
        Customer customer = customerOptional.get();
        customer.setName(form.getName());
        customer.setCpf(form.getCpf());
        return Optional.of(customer);
    }
}
