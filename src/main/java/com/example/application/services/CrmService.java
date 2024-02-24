package com.example.application.services;

import com.example.application.data.Manufacturers;
import com.example.application.data.ManufacturersRepository;
import com.example.application.data.Souvenirs;
import com.example.application.data.SouvenirsRepository;
import org.springframework.stereotype.Service;

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

    public void saveManufacturer(Manufacturers manufacturer) { // метод для збереження сувеніру
        if (manufacturer == null) {
            System.err.println("Сувеныр пустий. Ви впевнені, що хочете створити пустий сувенір?");
            return;
        }
        manufacturersRepository.save(manufacturer);
    }
    public void deleteManufacturer(Manufacturers manufacturer) {
        manufacturersRepository.delete(manufacturer);
    } // метод для видалення сувеніру


    // SOUVENIRS
    public long countSouvenirs() { // метод для підрахунку кількості сувенірів
        return souvenirsRepository.count();
    }

    public List<Souvenirs> findAllSouvenirs(String stringFilter) { // метод для пошуку всіх сувенірів
        if (stringFilter == null || stringFilter.isEmpty()) {
            return souvenirsRepository.findAll();
        } else {
            return souvenirsRepository.search(stringFilter);
        }
    }
    public void deleteSouvenir(Souvenirs souvenir) {
        souvenirsRepository.delete(souvenir);
    } // метод для видалення сувеніру

    public void saveSouvenir(Souvenirs souvenir) { // метод для збереження сувеніру
        if (souvenir == null) {
            System.err.println("Сувеныр пустий. Ви впевнені, що хочете створити пустий сувенір?");
            return;
        }
        souvenirsRepository.save(souvenir);
    }
}