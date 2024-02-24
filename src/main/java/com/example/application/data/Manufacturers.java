package com.example.application.data;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import org.hibernate.annotations.Formula;
@Entity
public class Manufacturers extends  AbstractEntity{
    @NotEmpty
    private String name;

    @NotEmpty
    private String country;
    @Email
    @NotEmpty
    private String email;

    @NotEmpty
    private String website;
    private String description;


    public void setName(String name) {
        this.name = name;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }


    public String getEmail() {
        return email;
    }

    public String getWebsite() {
        return website;
    }

    public String getDescription() {
        return description;
    }
    @Formula("(select count(c.id) from Souvenirs c where c.manufacturer_s_details = id)")
    private int manufacturerCount;
    public int getManufacturerCount(){
        return manufacturerCount;
    }
}
