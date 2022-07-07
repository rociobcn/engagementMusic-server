package com.engagementMusic.engagementMusic.Embeddables;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotEmpty;

@Embeddable
public class Address {
    @NotEmpty
    private String street;
    @NotEmpty
    private String city;
    @NotEmpty
    private String country;
    @NotEmpty
    private String postalCode;

    public Address() {
    }

    public Address(String street, String city, String country, String postalCode) {
        setStreet(street);
        setCity(city);
        setCountry(country);
        this.postalCode = postalCode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street.toUpperCase();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city.toUpperCase();
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country.toUpperCase();
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
}
