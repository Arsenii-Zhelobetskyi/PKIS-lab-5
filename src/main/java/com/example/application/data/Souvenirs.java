package com.example.application.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDate;
import java.util.Date;


/**
 * Сутність Souvenirs. Цей клас описує сутність Souvenirs, яка зберігається в базі даних. Кожен екземпляр цього класу відповідає одному рядку в таблиці бази даних.
 */
@Entity
public class Souvenirs extends AbstractEntity {
    @NotEmpty // потрібно для валідації даних
    private String name; // назва сувеніру
    private String manufacturer_s_details = "Unknown"; // деталі виробника, по факту його id
    @NotNull
    private LocalDate date; // дата виготовлення
    @NotNull
    private double price; // ціна сувеніру

    // далі йдуть гетери та сетери для полів класу. Їх використовує JPA для роботи з базою даних
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
