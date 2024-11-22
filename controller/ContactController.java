package controller;

import service.ContactService;

public class ContactController {
    private ContactService service = new ContactService();

    public void manage() {
        service.displayContacts();
    }

    public ContactService getService() {
        return service;
    }
}
