package com.jobportal.matching.controller;

import com.jobportal.matching.model.JobMatch;
import com.jobportal.matching.service.MatchingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/matching")
public class MatchingController {
    @Autowired
    private MatchingService matchingService;

    @GetMapping("/recommendations/{userId}")
    public ResponseEntity<List<JobMatch>> getJobMatches(@PathVariable Long userId) {
        return ResponseEntity.ok(matchingService.findBestMatches(userId));
    }
} 