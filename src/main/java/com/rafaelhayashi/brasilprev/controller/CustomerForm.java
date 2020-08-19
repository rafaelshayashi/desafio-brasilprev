package com.rafaelhayashi.brasilprev.controller;

import com.rafaelhayashi.brasilprev.model.Customer;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class CustomerForm {

    @NotEmpty
    private String name;

    @NotEmpty
    @Size(max = 14)
    private String cpf;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Customer convert() {
        return new Customer(this.name, this.cpf);
    }
}
