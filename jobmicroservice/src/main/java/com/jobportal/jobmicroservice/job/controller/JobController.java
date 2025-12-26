package com.jobportal.jobmicroservice.job.controller;



import com.jobportal.jobmicroservice.job.dto.JobDto;
import com.jobportal.jobmicroservice.job.model.Job;
import com.jobportal.jobmicroservice.job.service.JobService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
@CrossOrigin(origins = "*")
public class JobController {
    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    // CREATE
    @PostMapping
    public Job createJob(@RequestBody Job job) {
        return jobService.createJob(job);
    }

    // READ ALL
    @GetMapping
    public List<Job> getAllJobs() {
        return jobService.getAllJobs();
    }

    // READ BY ID
    @GetMapping("/{id}")
    public JobDto getJobById(@PathVariable Long id) {
        return jobService.getJobById(id);
    }

    // UPDATE
    @PutMapping("/{id}")
    public Job updateJob(@PathVariable Long id, @RequestBody Job job) {
        return jobService.updateJob(id, job);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public String deleteJob(@PathVariable Long id) {
        jobService.deleteJob(id);
        return "Job deleted successfully";

    }

    @GetMapping("/companys")
    public List<JobDto> getJobwithCompany(){
        return jobService.getAllJobWithCompany();
    }
}
