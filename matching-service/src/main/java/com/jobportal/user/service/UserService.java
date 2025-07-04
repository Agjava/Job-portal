package com.jobportal.user.service;

import com.jobportal.user.entity.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    public User getUserById(Long userId) {
        // TODO: Replace with actual inter-service call
        return new User(userId, "Dummy", "dummy@email.com", java.util.Set.of("Java"), "2 years", "Seattle");
    }
} 