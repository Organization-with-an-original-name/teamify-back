package org.original.name.teamify.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserRequest {
    private String username;
    private String firstName;
    private String lastName;
    private boolean lookingForTeam;

}
