package com.example.application.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

@Entity
public class Souvenirs extends AbstractEntity {

    @Id
    @NotEmpty
    private String id = "";

    @NotEmpty
    private String name = "";
    @NotEmpty
    private String manufacturer_s_details = "";

    @NotEmpty
    private Date date;


    @NotNull
    private double price;


    @Override
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
