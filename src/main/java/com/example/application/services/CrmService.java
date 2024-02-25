package com.example.application.services;

import com.example.application.data.Manufacturers;
import com.example.application.data.ManufacturersRepository;
import com.example.application.data.Souvenirs;
import com.example.application.data.SouvenirsRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/**
 * Сервіс для роботи з базою даних. Це потрібно, щоб ми не взаємодіяли з базою даних напряму з класів-контролерів.
 * Це дозволяє нам відокремити логіку доступу до даних від логіки відображення даних.
 */
@Service
public class CrmService {

    private final SouvenirsRepository souvenirsRepository;  // репозиторій для доступу до даних про сувеніри
    private final ManufacturersRepository manufacturersRepository;  // репозиторій для доступу до даних про сувеніри


    public CrmService(SouvenirsRepository souvenirsRepository, ManufacturersRepository manufacturersRepository ) { // конструктор класу
        this.souvenirsRepository = souvenirsRepository; // ініціалізація репозиторію
        this.manufacturersRepository = manufacturersRepository; // ініціалізація репозиторію
        // Отже, ми можемо використовувати CrmService у своєму додатку, не створюючи об'єкт вручну.
        // Spring буде автоматично створювати екземпляр CrmService та передавати в нього необхідні залежності,
        // такі як SouvenirsRepository.
    }


    // MANUFACTURERS



    public List<Manufacturers> findAllManufacturers(String stringFilter) { // метод для пошуку всіх виробників
        if (stringFilter == null || stringFilter.isEmpty()) {
            return manufacturersRepository.findAll();
        } else {
            return manufacturersRepository.search(stringFilter);
        }
    }
    public List<Manufacturers> searchManufacturersByField(String field, String value){ // метод для пошуку виробників за певним полем

        System.out.println("field: " + field + " value: " + value + " " + value.isEmpty());
        if (field.equals("name") && value != null && !value.isEmpty())
            return manufacturersRepository.searchByName(value);
        else if (field.equals("id") && value != null && !value.isEmpty())
            return manufacturersRepository.searchById(value);
        else if (field.equals("country") && value != null && !value.isEmpty())
            return manufacturersRepository.searchByCountry(value);
        else
            return findAllManufacturers("");
    }


    public void saveManufacturer(Manufacturers manufacturer) { // метод для збереження сувеніру в базі даних
        if (manufacturer == null) {
            System.err.println("Сувеныр пустий. Ви впевнені, що хочете створити пустий сувенір?");
            return;
        }
        manufacturersRepository.save(manufacturer);
    }
    public void deleteManufacturer(Manufacturers manufacturer) { // метод для видалення сувеніру з бази даних
        manufacturersRepository.delete(manufacturer);
    } // метод для видалення сувеніру


    // SOUVENIRS
    public List<Souvenirs> getSouvenirs() { // метод для пошуку всіх сувенірів
            return souvenirsRepository.findAll();
    } // метод для пошуку всіх сувенірів

    public List<Souvenirs> searchSouvenirsByField(String field, String value){ // метод для пошуку сувенірів за певним полем

        System.out.println("field: " + field + " value: " + value + " " + value.isEmpty());
        if (field.equals("name") && value != null && !value.isEmpty())
            return souvenirsRepository.searchByName(value);
        else if (field.equals("id") && value != null && !value.isEmpty())
            return souvenirsRepository.searchById(value);
        else
            return getSouvenirs();
    }

    public  List<Souvenirs> searchByPriceRange(double start, double end) { // метод для пошуку сувенірів за ціновим діапазоном
        return souvenirsRepository.searchByPriceRange(start, end);
    }

    public  List<Souvenirs> searchByDateRange(LocalDate start, LocalDate end) {     // метод для пошуку сувенірів за діапазоном дат
        return souvenirsRepository.searchByDateRange(start, end);
    }





    public void saveSouvenir(Souvenirs souvenir) { // метод для збереження сувеніру
        if (souvenir == null) {
            System.err.println("Сувенір пустий. Ви впевнені, що хочете створити пустий сувенір?");
            return;
        }
        souvenirsRepository.save(souvenir);
    }

    public void deleteSouvenir(Souvenirs souvenir) { // метод для видалення сувеніру
        souvenirsRepository.delete(souvenir);
    } // метод для видалення сувеніру




    public long countSouvenirs() { // метод для підрахунку кількості сувенірів
        return souvenirsRepository.count();
    } // метод для підрахунку кількості сувенірів
}