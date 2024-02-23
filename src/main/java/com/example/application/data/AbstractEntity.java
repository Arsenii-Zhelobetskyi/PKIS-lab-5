package com.example.application.data;

import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@MappedSuperclass
public abstract class AbstractEntity {

    @Id
    @UuidGenerator
    private String id;

    @Version
    private int version;

    public String getId() {


        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getVersion() {
        return version;
    }

    @Override
    public int hashCode() {
        if (getId() != null) {
            return getId().hashCode();
        }
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof AbstractEntity that)) {
            return false; // null or not an AbstractEntity class
        }
        if (getId() != null) {
            return getId().equals(that.getId());
        }
        return super.equals(that);
    }
//    @PrePersist
//    public void generateId() {
//        // Генерація 8-символьного UUID
//        this.id = UUID.randomUUID().toString().substring(0, 8);
//    }
}
