package org.original.name.teamify.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


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
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Contact> contacts = new ArrayList<>();

    public void addContact(Contact contact){
        contacts.add(contact);
        contact.setUser(this);
    }
//    private String location;
//    private String summary;
//    private Set<Skill> skills;

}
