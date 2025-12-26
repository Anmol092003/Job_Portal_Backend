package com.jobportal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "com.jobportal.company.model")
@EnableJpaRepositories(basePackages = "com.jobportal.company.repository")
public class CompanymicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CompanymicroserviceApplication.class, args);
	}

}
