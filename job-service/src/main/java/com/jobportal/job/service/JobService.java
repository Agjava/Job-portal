package com.jobportal.job.service;

import com.jobportal.job.entity.Job;
import com.jobportal.job.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

@Service
public class JobService {
    @Autowired
    private JobRepository jobRepository;

    private Map<String, List<Job>> locationCache = new HashMap<>();
    private PriorityQueue<Job> urgentJobs = new PriorityQueue<>(
            Comparator.comparing(Job::getPriority).reversed()
    );

    @PostConstruct
    public void init() {
        List<Job> jobs = jobRepository.findAll();
        for (Job job : jobs) {
            urgentJobs.offer(job);
        }
    }

    public List<Job> getJobsByLocation(String location) {
        return locationCache.computeIfAbsent(location,
                loc -> jobRepository.findByLocation(loc));
    }

    public Job getNextUrgentJob() {
        return urgentJobs.poll();
    }
} 