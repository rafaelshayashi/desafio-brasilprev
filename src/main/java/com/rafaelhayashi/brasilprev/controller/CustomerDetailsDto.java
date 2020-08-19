package com.rafaelhayashi.brasilprev.controller;

import com.rafaelhayashi.brasilprev.model.Customer;

import java.time.format.DateTimeFormatter;

public class CustomerDetailsDto {

    private final String uuid;
    private final String name;
    private final String cpf;
    private final String createdAt;
    private final String updatedAt;

    public CustomerDetailsDto(Customer customer){
        this.uuid = customer.getUuid().toString();
        this.name = customer.getName();
        this.cpf = customer.getCpf();
        this.createdAt = customer.getCreatedAt().format(DateTimeFormatter.ISO_DATE_TIME);
        this.updatedAt = customer.getUpdatedAt().format(DateTimeFormatter.ISO_DATE_TIME);
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

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }
}
