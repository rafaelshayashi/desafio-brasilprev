package com.rafaelhayashi.brasilprev.controller;

import com.rafaelhayashi.brasilprev.controller.dto.CustomerDetailsDto;
import com.rafaelhayashi.brasilprev.controller.dto.CustomerDto;
import com.rafaelhayashi.brasilprev.controller.form.CustomerAddressForm;
import com.rafaelhayashi.brasilprev.controller.form.CustomerForm;
import com.rafaelhayashi.brasilprev.controller.form.CustomerUpdateForm;
import com.rafaelhayashi.brasilprev.model.Customer;
import com.rafaelhayashi.brasilprev.service.CustomerService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.api.annotations.ParameterObject;
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
@Tag(name = "Customers")
public class CustomerController {


    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public Page<CustomerDto> list(@ParameterObject Pageable pageable) {
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

    @GetMapping("/{uuid}")
    public ResponseEntity<CustomerDetailsDto> details(@PathVariable String uuid) {
        Optional<Customer> customerOptional = this.customerService.details(uuid);
        return customerOptional
                .map(customer -> ResponseEntity.ok().body(new CustomerDetailsDto(customer)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<CustomerDetailsDto> update(@PathVariable String uuid, @RequestBody @Valid CustomerUpdateForm form){
        Optional<Customer> customerOptional = this.customerService.update(uuid, form);
        return customerOptional
                .map(customer -> ResponseEntity.ok().body(new CustomerDetailsDto(customer)))
                .orElseGet(ResponseEntity.notFound()::build);
    }

    @DeleteMapping("/{uuid}")
    @Transactional
    public ResponseEntity<?> delete(@PathVariable String uuid) {
        boolean deleted = this.customerService.delete(uuid);
        if (!deleted) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }

}
