package com.jobportal.jobmicroservice.mapper;

import com.jobportal.jobmicroservice.external.Review;
import com.jobportal.jobmicroservice.job.dto.JobDto;
import com.jobportal.jobmicroservice.external.Company;
import com.jobportal.jobmicroservice.job.model.Job;

import java.util.List;

public class JobMapper {

    public static JobDto moptoJobWithCompanyDto(Job job, Company company, List<Review> reviews){

        JobDto jobDto =new JobDto();

        jobDto.setId(job.getId());
        jobDto.setTitle(job.getTitle());
        jobDto.setDescription(job.getDescription());
        jobDto.setLocation(job.getLocation());
        jobDto.setMaxSalary(job.getMaxSalary());
        jobDto.setMinSalary(job.getMinSalary());

        jobDto.setCompany(company);

        jobDto.setReview(reviews);
        return jobDto;
    }
}
