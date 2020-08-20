package com.rafaelhayashi.brasilprev.repository;

import com.rafaelhayashi.brasilprev.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByUuid(UUID fromString);

    Optional<Customer> findByCpf(String value);
}
