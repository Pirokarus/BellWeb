package project.server;

import project.server.model.services.ContactService;

public class test {


    public static void main(String[] args) {
        ContactService contactService = ContactService.getInstance();

        System.out.println(contactService.login("user1","user1"));
    }
}
