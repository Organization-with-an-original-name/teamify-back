package org.original.name.teamify.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String username;
    private String firstName;
    private String lastName;
    private boolean lookingForTeam;
//    private String location;
//    private String summary;
//    private Set<Skill> skills;

}
