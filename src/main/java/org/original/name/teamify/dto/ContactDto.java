package org.original.name.teamify.dto;

import lombok.Getter;
import lombok.Setter;
import org.original.name.teamify.model.Contact;
import org.springframework.beans.BeanUtils;

@Getter
@Setter
public class ContactDto {
    Contact.ContactType type;
    String value;
    boolean shareable;

    public Contact toContact() {
        Contact contact = new Contact();
        BeanUtils.copyProperties(this, contact);
        return contact;
    }

    public static ContactDto ofContact(Contact contact) {
        ContactDto dto = new ContactDto();
        BeanUtils.copyProperties(contact, dto);
        return dto;
    }
}
