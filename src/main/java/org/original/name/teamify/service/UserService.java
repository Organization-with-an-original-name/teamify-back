package org.original.name.teamify.service;

import lombok.RequiredArgsConstructor;
import org.original.name.teamify.dto.ContactDto;
import org.original.name.teamify.dto.CreateUserRequest;
import org.original.name.teamify.exception.TeamifyException;
import org.original.name.teamify.model.Contact;
import org.original.name.teamify.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.original.name.teamify.repository.UserRepository;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User getUser(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new TeamifyException("User with id specified does not exist"));
    }

    public User getUser(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new TeamifyException("User with id specified does not exist"));
    }

    public User createUser(CreateUserRequest createUserRequest) {
        if (userRepository.existsByUsername(createUserRequest.getUsername())) {
            throw new TeamifyException("User with such username already exists");
        }
        if (CollectionUtils.isEmpty(createUserRequest.getContacts())) {
            throw new TeamifyException("At least one contact must be provided");
        }
        var user = new User();
        BeanUtils.copyProperties(createUserRequest, user);
        createUserRequest.getContacts().stream()
                .map(ContactDto::toContact)
                .forEach(user::addContact);
        userRepository.save(user);
        return user;
    }

    public List<Contact> getUserContacts(Long userId) {
        return getUser(userId).getContacts();
    }
}
