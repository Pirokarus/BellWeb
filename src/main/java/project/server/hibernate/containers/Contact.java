package project.server.hibernate.containers;

import project.server.hibernate.entities.ContactsEntity;

public class Contact {
    private int id;
    private String firstname;
    private String lastname;
    private String number;

    public Contact() {
    }

    public Contact(ContactsEntity contactsEntity){
        this.id = contactsEntity.getId();
        this.firstname = contactsEntity.getFirstname();
        this.lastname = contactsEntity.getLastname();
        this.number = contactsEntity.getNumber();
    }

    public ContactsEntity contactsEntity(){
        ContactsEntity contactsEntity = new ContactsEntity();
        contactsEntity.setId(this.id);
        contactsEntity.setFirstname(this.firstname);
        contactsEntity.setLastname(this.lastname);
        contactsEntity.setNumber(this.number);
        return contactsEntity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }


}
