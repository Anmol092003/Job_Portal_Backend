package com.jobportal.jobmicroservice.job.service;



import com.jobportal.jobmicroservice.job.dto.JobDto;
import com.jobportal.jobmicroservice.job.model.Job;

import java.util.List;

public interface JobService {
    Job createJob(Job job);

    JobDto getJobById(Long id);

    List<Job> getAllJobs();

    Job updateJob(Long id, Job job);

    void deleteJob(Long id);

    List<JobDto> getAllJobWithCompany();
}
