package com.example.application.data;

import jakarta.persistence.Entity;
import org.hibernate.annotations.Formula;
@Entity
public class Manufacturers extends  AbstractEntity{
    private String name;
    private String country;
    private String city;
    private String address;
    private String phone;
    private String email;
    private String website;
    private String description;



    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
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
