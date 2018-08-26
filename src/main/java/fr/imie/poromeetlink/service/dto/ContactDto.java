package fr.imie.poromeetlink.service.dto;

import fr.imie.poromeetlink.domain.entities.ContactId;

public class ContactDto {

    private ContactId id;

    public ContactId getId() {
        return id;
    }

    public void setId(ContactId id) {
        this.id = id;
    }
}
