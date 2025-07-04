package com.jobportal.user.trie;

import java.util.*;

public class SkillTrie {
    private TrieNode root;

    public SkillTrie() {
        root = new TrieNode();
    }

    public void insert(String skill) {
        TrieNode current = root;
        for (char c : skill.toLowerCase().toCharArray()) {
            current.children.putIfAbsent(c, new TrieNode());
            current = current.children.get(c);
        }
        current.isEndOfWord = true;
    }

    public List<String> searchWithPrefix(String prefix) {
        List<String> results = new ArrayList<>();
        TrieNode prefixNode = findPrefixNode(prefix.toLowerCase());
        if (prefixNode != null) {
            collectAllWords(prefixNode, prefix.toLowerCase(), results);
        }
        return results;
    }

    private TrieNode findPrefixNode(String prefix) {
        TrieNode current = root;
        for (char c : prefix.toCharArray()) {
            if (!current.children.containsKey(c)) {
                return null;
            }
            current = current.children.get(c);
        }
        return current;
    }

    private void collectAllWords(TrieNode node, String prefix, List<String> results) {
        if (node.isEndOfWord) {
            results.add(prefix);
        }
        for (Map.Entry<Character, TrieNode> entry : node.children.entrySet()) {
            collectAllWords(entry.getValue(), prefix + entry.getKey(), results);
        }
    }

    private static class TrieNode {
        Map<Character, TrieNode> children = new HashMap<>();
        boolean isEndOfWord = false;
    }
} 