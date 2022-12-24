package jk.uz.pdp.model;

import java.util.UUID;

public class Contact {
    private UUID userId;
    private UUID contactId; // bu ham user id aslida

    public Contact() {
    }

    public Contact(UUID userId, UUID contactId) {
        this.userId = userId;
        this.contactId = contactId;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public UUID getContactId() {
        return contactId;
    }

    public void setContactId(UUID contactId) {
        this.contactId = contactId;
    }
}
