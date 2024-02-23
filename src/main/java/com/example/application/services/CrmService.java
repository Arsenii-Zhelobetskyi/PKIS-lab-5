package com.example.application.services;

import com.example.application.data.Company;
import com.example.application.data.Souvenirs;
//import com.example.application.data.Status;
import com.example.application.data.CompanyRepository;
import com.example.application.data.SouvenirsRepository;
//import com.example.application.data.StatusRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CrmService {

    private final SouvenirsRepository souvenirsRepository;


    public CrmService(SouvenirsRepository souvenirsRepository) {
        this.souvenirsRepository = souvenirsRepository;

    }

    public List<Souvenirs> findAllContacts(String stringFilter) {
        if (stringFilter == null || stringFilter.isEmpty()) {
            return souvenirsRepository.findAll();
        } else {
//            return souvenirsRepository.search(stringFilter);
            return souvenirsRepository.findAll();
        }
    }
    public void deleteSouvenir(Souvenirs souvenir) {
        souvenirsRepository.delete(souvenir);
    }

    public void saveSouvenir(Souvenirs souvenir) {
        if (souvenir == null) {
            System.err.println("Contact is null. Are you sure you have connected your form to the application?");
            return;
        }
        souvenirsRepository.save(souvenir);
    }
}