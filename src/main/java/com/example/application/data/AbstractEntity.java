package com.example.application.data;

import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

/**
 * Абстрактний клас для всіх сутностей бази даних
 */

@MappedSuperclass
// анотація JPA для позначення того, що цей клас не є сутністю, але його поля будуть використовуватися в сутностях, які його розширюють
public abstract class AbstractEntity {

    // JPA - Java Persistence API - набір класів і методів для роботи з базами даних
    @Id // анотація JPA для позначення того, що це поле є первинним ключем
    @UuidGenerator // анотація Hibernate для генерації UUID
    private String id;


    // далі йдуть гетери та сетери для поля. Їх використовує JPA для роботи з базою даних
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    @Override // перевизначення методу hashCode для коректної роботи з колекціями
    public int hashCode() {
        if (getId() != null) {
            return getId().hashCode();
        }
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) { // Цей метод equals() перевизначений для порівняння об'єктів класу AbstractEntity з іншими об'єктами.
        if (!(obj instanceof AbstractEntity that)) {
            return false;
        }
        if (getId() != null) {
            return getId().equals(that.getId());
        }
        return super.equals(that);
    }
}
