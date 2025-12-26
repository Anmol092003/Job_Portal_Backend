package com.jobportal.jobmicroservice.clients;

import com.jobportal.jobmicroservice.external.Company;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="COMPANYMICROSERVICE")
public interface CompnayClient {
    @GetMapping("/api/companies/{id}")
    Company getCompany(@PathVariable("id") Long id);
}
