package org.original.name.teamify.controller;

import lombok.RequiredArgsConstructor;
import org.original.name.teamify.dto.AuthRequest;
import org.original.name.teamify.service.IdentityService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class IdentityController {
    private final IdentityService identityService;


    @PostMapping
    public String authenticate(@RequestBody AuthRequest request){
       return identityService.getToken(request.getUsername(), request.getPassword());
    }
}
