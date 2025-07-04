package com.jobportal.matching.service;

import com.jobportal.matching.model.JobMatch;
import com.jobportal.job.entity.Job;
import com.jobportal.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class MatchingService {
    @Autowired
    private com.jobportal.user.service.UserService userService;
    @Autowired
    private com.jobportal.job.service.JobService jobService;

    public class SkillGraph {
        private Map<String, List<String>> adjacencyList = new HashMap<>();

        public void addSkillRelation(String skill1, String skill2) {
            adjacencyList.computeIfAbsent(skill1, k -> new ArrayList<>()).add(skill2);
            adjacencyList.computeIfAbsent(skill2, k -> new ArrayList<>()).add(skill1);
        }

        public double calculateSkillSimilarity(Set<String> userSkills, Set<String> jobSkills) {
            int matches = 0;
            for (String userSkill : userSkills) {
                if (jobSkills.contains(userSkill)) {
                    matches++;
                } else {
                    matches += findRelatedSkills(userSkill, jobSkills) ? 1 : 0;
                }
            }
            return (double) matches / Math.max(userSkills.size(), jobSkills.size());
        }

        private boolean findRelatedSkills(String userSkill, Set<String> jobSkills) {
            Set<String> visited = new HashSet<>();
            Queue<String> queue = new LinkedList<>();
            queue.add(userSkill);
            visited.add(userSkill);
            while (!queue.isEmpty()) {
                String current = queue.poll();
                if (jobSkills.contains(current)) {
                    return true;
                }
                for (String neighbor : adjacencyList.getOrDefault(current, Collections.emptyList())) {
                    if (!visited.contains(neighbor)) {
                        visited.add(neighbor);
                        queue.add(neighbor);
                    }
                }
            }
            return false;
        }
    }

    public List<JobMatch> findBestMatches(Long userId) {
        User user = userService.getUserById(userId);
        List<Job> allJobs = jobService.getAllActiveJobs();
        SkillGraph skillGraph = new SkillGraph(); // In real use, should be built from data
        PriorityQueue<JobMatch> matches = new PriorityQueue<>(
                Comparator.comparing(JobMatch::getMatchScore).reversed()
        );
        for (Job job : allJobs) {
            double score = skillGraph.calculateSkillSimilarity(user.getSkills(), job.getRequiredSkills());
            if (score > 0.3) {
                matches.offer(new JobMatch(job, score));
            }
        }
        return matches.stream().limit(10).collect(Collectors.toList());
    }
} 