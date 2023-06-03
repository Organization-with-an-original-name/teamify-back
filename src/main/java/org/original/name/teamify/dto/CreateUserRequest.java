package org.original.name.teamify.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CreateUserRequest {
    private String username;
    private String firstName;
    private String lastName;
    private String location;
    private String password;
    private boolean lookingForTeam;
    private List<ContactDto> contacts;
}
