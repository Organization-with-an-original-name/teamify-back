package org.original.name.teamify.service;

import lombok.RequiredArgsConstructor;
import org.original.name.teamify.exception.UnauthenticatedException;
import org.original.name.teamify.model.User;
import org.original.name.teamify.repository.UserRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class IdentityService {
    private final UserRepository repository;
    private Map<String, User> authenticatedUsers = new HashMap<>();
    private Map<String, LocalDateTime> lastAccessed = new HashMap<>();

    @Scheduled(fixedRate = 10_000)
    private void revokeUnusedTokens() {
        var cutoffTime = LocalDateTime.now().minus(3, ChronoUnit.HOURS);
        var toRemove = lastAccessed.entrySet().stream()
                .filter(e -> e.getValue().isBefore(cutoffTime))
                .map(Map.Entry::getKey)
                .toList();
        toRemove.forEach(authenticatedUsers::remove);
        toRemove.forEach(lastAccessed::remove);
    }

    public User currentUser(String token) {
        var user = authenticatedUsers.get(token);
        if (token == null) {
            throw new UnauthenticatedException();
        }
        lastAccessed.put(token, LocalDateTime.now());
        return user;
    }

    public String getToken(String userName, String password) {
        return repository.findByUsernameAndPassword(userName, password)
                .map(this::registerToken)
                .orElseThrow(UnauthenticatedException::new);
    }

    public String registerToken(User user) {
        String token = UUID.randomUUID().toString();
        authenticatedUsers.put(token, user);
        lastAccessed.put(token, LocalDateTime.now());
        return token;
    }
}
