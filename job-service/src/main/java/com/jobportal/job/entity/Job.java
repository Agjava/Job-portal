package com.jobportal.job.entity;

import lombok.Data;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Data
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String company;
    @ElementCollection
    private Set<String> requiredSkills;
    private String experienceLevel;
    private String location;
    private LocalDateTime postedDate;
    private Integer priority;
} 