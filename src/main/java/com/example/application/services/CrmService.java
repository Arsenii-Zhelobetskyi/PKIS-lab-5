package com.example.application.services;

import com.example.application.data.Company;
import com.example.application.data.Souvenirs;
import com.example.application.data.Status;
import com.example.application.data.CompanyRepository;
import com.example.application.data.SouvenirsRepository;
import com.example.application.data.StatusRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CrmService {

    private final SouvenirsRepository souvenirsRepository;
    private final CompanyRepository companyRepository;
    private final StatusRepository statusRepository;

    public CrmService(SouvenirsRepository souvenirsRepository,
                      CompanyRepository companyRepository,
                      StatusRepository statusRepository) {
        this.souvenirsRepository = souvenirsRepository;
        this.companyRepository = companyRepository;
        this.statusRepository = statusRepository;
    }

    public List<Souvenirs> findAllContacts(String stringFilter) {
        if (stringFilter == null || stringFilter.isEmpty()) {
            return souvenirsRepository.findAll();
        } else {
//            return souvenirsRepository.search(stringFilter);
            return souvenirsRepository.findAll();
        }
    }

    public long countContacts() {
        return souvenirsRepository.count();
    }

    public void deleteContact(Souvenirs souvenirs) {
        souvenirsRepository.delete(souvenirs);
    }

    public void saveContact(Souvenirs souvenirs) {
        if (souvenirs == null) {
            System.err.println("Contact is null. Are you sure you have connected your form to the application?");
            return;
        }
        souvenirsRepository.save(souvenirs);
    }

    public List<Company> findAllCompanies() {
        return companyRepository.findAll();
    }

    public List<Status> findAllStatuses(){
        return statusRepository.findAll();
    }
}