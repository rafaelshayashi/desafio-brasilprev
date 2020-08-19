package com.rafaelhayashi.brasilprev.controller;

import com.rafaelhayashi.brasilprev.model.Customer;
import org.springframework.data.domain.Page;

public class CustomerDto {

    private final String uuid;
    private final String name;
    private final String cpf;

    private CustomerDto(Customer customer){
        this.uuid = customer.getUuid().toString();
        this.name = customer.getName();
        this.cpf = customer.getCpf();
    }

    public static Page<CustomerDto> convert(Page<Customer> customers) {
        return customers.map(CustomerDto::new);
    }

    public String getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public String getCpf() {
        return cpf;
    }
}
