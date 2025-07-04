package com.jobportal.user.service;

import com.jobportal.user.entity.User;
import com.jobportal.user.repository.UserRepository;
import com.jobportal.user.trie.SkillTrie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    private SkillTrie skillTrie = new SkillTrie();

    @PostConstruct
    public void init() {
        // Load all skills from users into the Trie
        List<User> users = userRepository.findAll();
        for (User user : users) {
            for (String skill : user.getSkills()) {
                skillTrie.insert(skill);
            }
        }
    }

    public User createUser(User user) {
        User saved = userRepository.save(user);
        for (String skill : user.getSkills()) {
            skillTrie.insert(skill);
        }
        return saved;
    }

    public List<String> getSkillSuggestions(String prefix) {
        return skillTrie.searchWithPrefix(prefix);
    }
} 