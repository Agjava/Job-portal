package com.jobportal.job.entity;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.Set;

@Data
public class Job {
    private Long id;
    private String title;
    private String company;
    private Set<String> requiredSkills;
    private String experienceLevel;
    private String location;
    private LocalDateTime postedDate;
    private Integer priority;
} 