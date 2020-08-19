package com.rafaelhayashi.brasilprev.controller;

import com.rafaelhayashi.brasilprev.model.Customer;
import com.rafaelhayashi.brasilprev.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {


    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public Page<CustomerDto> list(Pageable pageable) {
        Page<Customer> customers = this.customerService.list(pageable);
        return CustomerDto.convert(customers);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<CustomerDetailsDto> create(@RequestBody @Valid CustomerForm customerForm,
                                                     UriComponentsBuilder uriBuilder) {
        Customer customer = this.customerService.create(customerForm);
        URI uri = uriBuilder.path("/api/v1/customers/{uuid}").buildAndExpand(customer.getUuid().toString()).toUri();
        return ResponseEntity.created(uri).body(new CustomerDetailsDto(customer));
    }

    @PostMapping("/{uuid}/address")
    @Transactional
    public ResponseEntity<CustomerDetailsDto> createAddress(@PathVariable String uuid,
                                                            @RequestBody @Valid CustomerAddressForm form,
                                                            UriComponentsBuilder uriBuilder) {
        Optional<Customer> customerOptional = this.customerService.createAddress(uuid, form);
        return customerOptional
                .map(customer -> {
                    URI uri = uriBuilder.path("/api/v1/customers/{uuid}/address").buildAndExpand(customer.getUuid().toString()).toUri();
                    return ResponseEntity.created(uri).body(new CustomerDetailsDto(customer));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}
