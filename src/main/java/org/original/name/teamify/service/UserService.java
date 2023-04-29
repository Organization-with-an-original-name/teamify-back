package org.original.name.teamify.service;

import com.fasterxml.jackson.databind.util.BeanUtil;
import lombok.RequiredArgsConstructor;
import org.original.name.teamify.dto.CreateUserRequest;
import org.original.name.teamify.exception.TeamifyException;
import org.original.name.teamify.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.original.name.teamify.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User getUser(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new TeamifyException("User with id specified does not exist"));
    }

    public User createUser(CreateUserRequest createUserRequest) {
        if (userRepository.existsByUsername(createUserRequest.getUsername())) {
            throw new TeamifyException("User with such username already exists");
        }
        User user = new User();
        BeanUtils.copyProperties(createUserRequest, user);
        userRepository.save(user);
        return user;
    }


}
