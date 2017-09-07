package project.server.hibernate.dao;

import project.server.hibernate.entities.ContactsEntity;
import project.server.hibernate.entities.UsersEntity;

import java.util.List;

public interface ContactsDAO {

    void save(ContactsEntity contactsEntity);

    List<ContactsEntity> findAll(UsersEntity user_id);

    ContactsEntity findById(int id);

    void deleteById(int id);

    void updateContact(ContactsEntity contactsEntity);

}
