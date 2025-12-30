package com.jobportal.jobmicroservice.job.service;



import com.jobportal.jobmicroservice.clients.CompnayClient;
import com.jobportal.jobmicroservice.clients.ReviewClient;
import com.jobportal.jobmicroservice.external.Review;
import com.jobportal.jobmicroservice.job.dto.JobDto;
import com.jobportal.jobmicroservice.external.Company;
import com.jobportal.jobmicroservice.job.model.Job;
import com.jobportal.jobmicroservice.job.repository.JobRepository;
import com.jobportal.jobmicroservice.mapper.JobMapper;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JobServiceImpl implements JobService {

    @Autowired
    private RestTemplate restTemplate;

    private CompnayClient compnayClient;

    private ReviewClient reviewClient;

    private final JobRepository jobRepository;

    int attempt=0;

    public JobServiceImpl(JobRepository jobRepository, ReviewClient reviewClient, CompnayClient compnayClient) {
        this.jobRepository = jobRepository;
        this.reviewClient = reviewClient;
        this.compnayClient = compnayClient;
    }

    @Override
    public Job createJob(Job job) {
        return jobRepository.save(job);
    }

    @Override
    public JobDto getJobById(Long id) {
        Job job= jobRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Job not found with id: " + id));
      JobDto jobDto = converToDto(job);
      return jobDto;
    }

    @Override
    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }

    @Override
    public Job updateJob(Long id, Job job) {
        Job existingJob = jobRepository.findById(id).orElseThrow(()->new RuntimeException());

        existingJob.setTitle(job.getTitle());
        existingJob.setDescription(job.getDescription());
        existingJob.setMinSalary(job.getMinSalary());
        existingJob.setMaxSalary(job.getMaxSalary());
        existingJob.setLocation(job.getLocation());

        return jobRepository.save(existingJob);
    }

    @Override
    public void deleteJob(Long id) {
        jobRepository.deleteById(id);
    }

    @Override
    @RateLimiter(name = "companyBreaker",fallbackMethod = "fallBackGetAllJobWithCompany")
//    @Retry(name = "companyBreaker" )
//    @CircuitBreaker(name = "companyBreaker" ,fallbackMethod = "fallBackGetAllJobWithCompany")
    public List<JobDto> getAllJobWithCompany(){

        System.out.println("Attempts : "+ ++attempt);

        List<Job> jobs=getAllJobs();

        return jobs.stream().map(this::converToDto).collect(Collectors.toList());
    }
    public List<String> fallBackGetAllJobWithCompany(Exception e){
        List<String > list=new ArrayList<>();
        list.add("temp");
        list.add("temp2");
        return list;
    }

    private JobDto converToDto(Job job){
       Company company=compnayClient.getCompany(job.getCompanyId());

        String url="http://REVIEWMICROSERVICE/api/companies/" + job.getCompanyId() + "/reviews";

        List<Review> reviews=reviewClient.getReviews(job.getCompanyId());

//        List<Review> reviews =
//                reviewResponse.getBody() != null
//                        ? reviewResponse.getBody()
//                        : List.of();

        return JobMapper.moptoJobWithCompanyDto(job,company,reviews);
    }

}
