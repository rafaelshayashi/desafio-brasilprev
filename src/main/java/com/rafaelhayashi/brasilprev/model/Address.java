package com.rafaelhayashi.brasilprev.model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDateTime;

@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Column(nullable = false)
    private String country;

    @NotEmpty
    @Column(nullable = false)
    private String state;

    @NotEmpty
    @Column(nullable = false)
    private String city;

    @NotEmpty
    @Column(nullable = false)
    private String street;

    @NotEmpty
    @Column(nullable = false)
    private String postalCode;

    @CreationTimestamp
    @PastOrPresent
    @Column(nullable = false)
    private LocalDateTime createdAt;

    @CreationTimestamp
    @PastOrPresent
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @Deprecated
    public Address(){}

    public Address(String country, String state, String city, String street, String postalCode) {
        this.country = country;
        this.state = state;
        this.city = city;
        this.street = street;
        this.postalCode = postalCode;
    }

    public Long getId() {
        return id;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", country='" + country + '\'' +
                ", state='" + state + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address)) return false;

        Address address = (Address) o;

        if (!getCountry().equals(address.getCountry())) return false;
        if (!getState().equals(address.getState())) return false;
        if (!getCity().equals(address.getCity())) return false;
        if (!getStreet().equals(address.getStreet())) return false;
        return getPostalCode().equals(address.getPostalCode());
    }

    @Override
    public int hashCode() {
        int result = getCountry().hashCode();
        result = 31 * result + getState().hashCode();
        result = 31 * result + getCity().hashCode();
        result = 31 * result + getStreet().hashCode();
        result = 31 * result + getPostalCode().hashCode();
        return result;
    }
}
