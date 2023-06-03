package org.original.name.teamify.controller;

import lombok.RequiredArgsConstructor;
import org.original.name.teamify.dto.ContactDto;
import org.original.name.teamify.dto.CreateUserRequest;
import org.original.name.teamify.dto.UserDto;
import org.original.name.teamify.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@CrossOrigin("*")
public class UserController {
    private final UserService userService;

    @GetMapping("/{userId}")
    public UserDto getUser(@PathVariable Long userId){
        return UserDto.ofUser(userService.getUser(userId));
    }

    @GetMapping()
    public UserDto getUserByUsername(@RequestParam String username){
        return UserDto.ofUser(userService.getUser(username));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto createUser(@RequestBody CreateUserRequest createUserRequest){
        return UserDto.ofUser(userService.createUser(createUserRequest));
    }

    @GetMapping("/{userId}/contacts")
    public List<ContactDto> getUserContacts(@PathVariable Long userId){
       return userService.getUserContacts(userId).stream()
               .map(ContactDto::ofContact)
               .toList();

    }
}
