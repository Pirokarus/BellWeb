package project.server.hibernate.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.server.hibernate.dao.ContactsDAO;
import project.server.hibernate.entities.ContactsEntity;
import project.server.hibernate.entities.UsersEntity;


import java.util.List;

@Service("contactsService")
@Transactional
public class ContactsServiceImpl implements ContactsService {

    @Autowired
    private ContactsDAO contactsDAO;

    @Override
    public void save(ContactsEntity contactsEntity) {
        contactsDAO.save(contactsEntity);
    }

    @Override
    public List<ContactsEntity> findAll(UsersEntity user_id) {
        return contactsDAO.findAll(user_id);
    }

    @Override
    public ContactsEntity findById(int id) {
        return contactsDAO.findById(id);
    }

    @Override
    public void deleteById(int id) {
        contactsDAO.deleteById(id);
    }

    @Override
    public void updateContact(ContactsEntity contactsEntity) {
        contactsDAO.updateContact(contactsEntity);
    }
}
