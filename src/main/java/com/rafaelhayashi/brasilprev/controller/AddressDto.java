package com.rafaelhayashi.brasilprev.controller;

import com.rafaelhayashi.brasilprev.model.Address;

import java.time.format.DateTimeFormatter;

public class AddressDto {

    private final String country;
    private final String state;
    private final String city;
    private final String street;
    private final String postalCode;
    private final String createdAt;
    private final String updatedAt;

    public AddressDto(Address address) {
        this.country = address.getCountry();
        this.state = address.getState();
        this.city = address.getCity();
        this.street = address.getStreet();
        this.postalCode = address.getPostalCode();
        this.createdAt = address.getCreatedAt().format(DateTimeFormatter.ISO_DATE_TIME);
        this.updatedAt = address.getCreatedAt().format(DateTimeFormatter.ISO_DATE_TIME);
    }

    public String getCountry() {
        return country;
    }

    public String getState() {
        return state;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }
}
