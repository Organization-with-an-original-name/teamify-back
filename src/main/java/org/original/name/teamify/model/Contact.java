package org.original.name.teamify.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Contact {
    @Id
    @GeneratedValue
    Long id;
    @ManyToOne
    User user;
    ContactType type;
    String value;
    boolean shareable;

    public enum ContactType {
        PHONE,
        TELEGRAM,
        EMAIL,
        SKYPE,
        LINKEDIN
    }
}
