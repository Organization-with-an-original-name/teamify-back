package org.original.name.teamify.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateTeamRequest {
    Long leaderId;
    String name;
    String description;
}
