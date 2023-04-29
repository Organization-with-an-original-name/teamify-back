package org.original.name.teamify.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
public class Team {
    @Id
    @GeneratedValue
    Long id;

    @Column(unique = true)
    String name;
    String description;

    @OneToOne
    User leader;
    @ManyToMany
    Set<User> members = new HashSet<>();

}