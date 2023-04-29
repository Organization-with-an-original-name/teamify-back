package org.original.name.teamify.dto;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CreateUserRequest {
    private String username;
    private String firstName;
    private String lastName;
    private boolean lookingForTeam;
    private List<ContactDto> contacts;

}
