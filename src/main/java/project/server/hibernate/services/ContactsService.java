package project.server.hibernate.services;


import project.server.hibernate.entities.ContactsEntity;
import project.server.hibernate.entities.UsersEntity;

import java.util.List;

public interface ContactsService{

    void save(ContactsEntity contactsEntity);

    List<ContactsEntity> findAll(UsersEntity user_id) ;

    ContactsEntity findById(int id) ;

    void deleteById(int id) ;

    void updateContact(ContactsEntity contactsEntity) ;
}
