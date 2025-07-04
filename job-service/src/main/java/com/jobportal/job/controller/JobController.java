package com.jobportal.job.controller;

import com.jobportal.job.entity.Job;
import com.jobportal.job.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/jobs")
public class JobController {
    @Autowired
    private JobService jobService;

    @GetMapping("/search")
    public ResponseEntity<List<Job>> searchJobs(@RequestParam String location,
                                                @RequestParam(required = false) Set<String> skills) {
        List<Job> jobs = jobService.getJobsByLocation(location);
        if (skills != null && !skills.isEmpty()) {
            jobs = jobs.stream()
                    .filter(job -> job.getRequiredSkills().containsAll(skills))
                    .collect(Collectors.toList());
        }
        return ResponseEntity.ok(jobs);
    }

    @GetMapping("/urgent")
    public ResponseEntity<Job> getNextUrgentJob() {
        return ResponseEntity.ok(jobService.getNextUrgentJob());
    }
} 