package com.example.application.services;

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


    public CrmService(SouvenirsRepository souvenirsRepository) { // конструктор класу
        this.souvenirsRepository = souvenirsRepository; // ініціалізація репозиторію
        // Отже, ми можемо використовувати CrmService у своєму додатку, не створюючи об'єкт вручну.
        // Spring буде автоматично створювати екземпляр CrmService та передавати в нього необхідні залежності,
        // такі як SouvenirsRepository.
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