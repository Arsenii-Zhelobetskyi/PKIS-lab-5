package com.example.application.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ManufacturersRepository extends JpaRepository<Manufacturers, String> {

    // Тут ми можемо визначити власні методи для доступу до даних.
    // Наприклад, метод пошуку сувенірів за назвою
    @Query("select c from Souvenirs c " +
            "where lower(c.name) like lower(concat('%', :searchTerm, '%')) ")
    List<Manufacturers> search(@Param("searchTerm") String searchTerm);
}