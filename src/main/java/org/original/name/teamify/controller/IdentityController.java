package org.original.name.teamify.controller;

import lombok.RequiredArgsConstructor;
import org.original.name.teamify.dto.AuthRequest;
import org.original.name.teamify.service.IdentityService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
@CrossOrigin("*")
public class IdentityController {
    private final IdentityService identityService;


    @PostMapping
    public String authenticate(@RequestBody AuthRequest request){
       return identityService.getToken(request.getUsername(), request.getPassword());
    }
}
