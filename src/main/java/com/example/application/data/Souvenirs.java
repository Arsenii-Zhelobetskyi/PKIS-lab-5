package com.example.application.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UuidGenerator;

import java.util.Date;

@Entity
public class Souvenirs extends AbstractEntity {


    @NotEmpty
    private String name = "";


    private String manufacturer_s_details = "";

    @NotEmpty
    private String date;

    @NotNull
    private double price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManufacturer_s_details() {
        return manufacturer_s_details;
    }

    public void setManufacturer_s_details(String manufacturer_s_details) {
        this.manufacturer_s_details = manufacturer_s_details;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
