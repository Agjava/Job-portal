package com.jobportal.matching.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.jobportal.job.entity.Job;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobMatch {
    private Job job;
    private double matchScore;
} 