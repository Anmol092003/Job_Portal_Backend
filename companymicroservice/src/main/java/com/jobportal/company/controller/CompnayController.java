package com.jobportal.company.controller;


import com.jobportal.company.model.Company;
import com.jobportal.company.service.CompanyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/companies")
public class CompnayController {

    private CompanyService companyService;

    public CompnayController(CompanyService companyService) {
        this.companyService = companyService;
    }

    // 1️⃣ Get all companies
    @GetMapping
    public ResponseEntity<List<Company>> getAllCompanies() {
        List<Company> companies = companyService.getAllCompanies();
        return ResponseEntity.ok(companies);
    }

    // 2️⃣ Create company
    @PostMapping
    public ResponseEntity<String> createCompany(@RequestBody Company company) {
        companyService.createCompany(company);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("Company created successfully");
    }

    // 3️⃣ Get company by ID
    @GetMapping("/{id}")
    public ResponseEntity<Company> getCompanyById(@PathVariable Long id) {
        Company company = companyService.getCompanyById(id);
        return ResponseEntity.ok(company);
    }

    // 4️⃣ Update company
    @PutMapping("/{id}")
    public ResponseEntity<String> updateCompany(
            @PathVariable Long id,
            @RequestBody Company updatedCompany) {

        boolean updated = companyService.updatecompany(id, updatedCompany);

        if (updated) {
            return ResponseEntity.ok("Company updated successfully");
        } else {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Company not found");
        }
    }

    // 5️⃣ Delete company
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCompany(@PathVariable Long id) {

        boolean deleted = companyService.deleteCompanyById(id);

        if (deleted) {
            return ResponseEntity.ok("Company deleted successfully");
        } else {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Company not found");
        }
    }
}
