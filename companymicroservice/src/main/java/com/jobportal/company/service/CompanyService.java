package com.jobportal.company.service;



import com.jobportal.company.model.Company;

import java.util.List;

public interface CompanyService {

    List<Company> getAllCompanies();

    void createCompany(Company company);

    Company getCompanyById(Long id);

    boolean deleteCompanyById(Long id);

    boolean updatecompany(Long id, Company updatedCompany);
}
