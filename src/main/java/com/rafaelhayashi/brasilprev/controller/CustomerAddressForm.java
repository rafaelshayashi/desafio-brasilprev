package com.rafaelhayashi.brasilprev.controller;

import com.rafaelhayashi.brasilprev.model.Address;

import javax.validation.constraints.NotEmpty;

public class CustomerAddressForm {

    @NotEmpty
    private String country;

    @NotEmpty
    private String state;

    @NotEmpty
    private String city;

    @NotEmpty
    private String street;

    @NotEmpty
    private String postalCode;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public Address convert() {
        return new Address(this.country, this.state, this.city, this.street, this.postalCode);
    }
}
