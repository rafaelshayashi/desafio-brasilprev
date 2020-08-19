package com.rafaelhayashi.brasilprev.repository;

import com.rafaelhayashi.brasilprev.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
