package com.jobportal.job.service;

import com.jobportal.job.entity.Job;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Service
public class JobService {
    public List<Job> getAllActiveJobs() {
        // TODO: Replace with actual inter-service call
        Job job = new Job();
        job.setId(1L);
        job.setTitle("Java Developer");
        job.setCompany("Amazon");
        job.setRequiredSkills(Set.of("Java", "Spring"));
        job.setExperienceLevel("Mid");
        job.setLocation("Seattle");
        job.setPostedDate(LocalDateTime.now());
        job.setPriority(10);
        return List.of(job);
    }
} 