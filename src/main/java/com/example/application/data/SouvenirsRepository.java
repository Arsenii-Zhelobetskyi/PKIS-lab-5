package com.example.application.data;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;


/**
 * У вказаному коді інтерфейсу SouvenirsRepository використовується Spring Data JPA для створення
 * репозиторію для доступу до даних про сувеніри. Він надає автоматично методи CRUD
 * <p>
 * Цей репозиторій дозволяє нам взаємодіяти з базою даних, зберігати, оновлювати, видаляти та шукати сувеніри за певними критеріями.
 * Використовуючи Spring Data JPA, ми можемо визначати власні методи запитів і виконувати їх у моїх програмах.
 */

public interface SouvenirsRepository extends JpaRepository<Souvenirs, String> {

    // Тут ми можемо визначити власні методи для доступу до даних.
    // Наприклад, метод пошуку сувенірів за назвою
//    @Query("select c from Souvenirs c " +
//            "where lower(c.name) like lower(concat('%', :searchTerm, '%')) ")
//    List<Souvenirs> searchByField(@Param("searchTerm") String searchTerm);

    @Query("SELECT c FROM Souvenirs c WHERE c.name=:searchValue")
    List<Souvenirs> searchByName(@Param("searchValue") String searchValue);

    @Query("SELECT c FROM Souvenirs c WHERE c.id=:searchValue")
    List<Souvenirs> searchById(@Param("searchValue") String searchValue);

    // query to search in range of dates
    @Query("SELECT c FROM Souvenirs c WHERE c.date BETWEEN :startDate AND :endDate")
    List<Souvenirs> searchByDateRange(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Query("SELECT c FROM Souvenirs c WHERE c.price BETWEEN :startPrice AND :endPrice")
    List<Souvenirs> searchByPriceRange(@Param("startPrice") double startPrice, @Param("endPrice") double endPrice);

}
