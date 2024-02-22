package com.example.application.data;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SouvenirsRepository extends JpaRepository<Souvenirs, Long> {

    @Query("select c from Souvenirs c " +
            "where lower(c.name) like lower(concat('%', :searchTerm, '%')) ")
    List<Souvenirs> search(@Param("searchTerm") String searchTerm);
}
