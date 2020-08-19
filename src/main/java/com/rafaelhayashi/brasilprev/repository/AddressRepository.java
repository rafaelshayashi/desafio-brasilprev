package com.rafaelhayashi.brasilprev.repository;

import com.rafaelhayashi.brasilprev.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
