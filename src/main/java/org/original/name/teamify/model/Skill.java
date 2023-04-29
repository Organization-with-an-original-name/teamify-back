package org.original.name.teamify.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Skill {
    @Id
    @GeneratedValue
    Long id;
    String name;
}
