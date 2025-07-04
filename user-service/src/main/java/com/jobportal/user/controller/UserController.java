package com.jobportal.user.controller;

import com.jobportal.user.entity.User;
import com.jobportal.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return ResponseEntity.ok(userService.createUser(user));
    }

    @GetMapping("/skills/autocomplete")
    public ResponseEntity<List<String>> getSkillSuggestions(@RequestParam String prefix) {
        return ResponseEntity.ok(userService.getSkillSuggestions(prefix));
    }
} 