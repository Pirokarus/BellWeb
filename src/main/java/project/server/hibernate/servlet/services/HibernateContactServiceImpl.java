package project.server.hibernate.servlet.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.server.hibernate.dao.ContactsDAO;
import project.server.hibernate.dao.GroupsDAO;
import project.server.hibernate.dao.ReferencesTableDAO;
import project.server.hibernate.dao.UsersDAO;
import project.server.hibernate.entities.ContactsEntity;
import project.server.hibernate.entities.ReferencesTableEntity;
import project.server.hibernate.entities.UsersEntity;
import project.server.model.jdbc.data.Contact;

import java.util.HashSet;
import java.util.Set;

@Service("servletContactsService")
@Transactional
public class HibernateContactServiceImpl /*implements AbstractContactService*/ {

    @Autowired
    ContactsDAO contactsDAO;

    @Autowired
    GroupsDAO groupsDAO;

    @Autowired
    ReferencesTableDAO referencesTableDAO;

    @Autowired
    UsersDAO usersDAO;


    //@Override
    public void save(Contact contact, int user_id) throws Exception {
        contactsDAO.save(contact.contactsEntity(usersDAO.findById(user_id)));
    }

    //@Override
    public void removeById(int id) throws Exception {
        contactsDAO.deleteById(id);
    }

    //@Override
    public void update(Contact contact, int id, int user_id) throws Exception {
        contactsDAO.updateContact(contact.contactsEntity(usersDAO.findById(user_id)));
    }

    //@Override
    public Set<Contact> getAll(int user_id) throws Exception {
        Set<Contact> contacts = new HashSet<>();
        for (ContactsEntity contactsEntity:contactsDAO.findAll(usersDAO.findById(user_id))){
            contacts.add(new Contact(contactsEntity,
                    referencesTableDAO.findAllByContactId(contactsEntity.getId())));
        }
        return contacts;
    }

    //@Override
    public Contact getById(int id) throws Exception {
        return new Contact(contactsDAO.findById(id),referencesTableDAO.findAllByContactId(id));
    }

    //@Override
    public void addContactGroup(int idC, int idG) {
        ReferencesTableEntity referencesTableEntity = new ReferencesTableEntity();
        referencesTableEntity.setContactsEntity(contactsDAO.findById(idC));
        referencesTableEntity.setGroupsEntity(groupsDAO.findById(idG));
        referencesTableDAO.save(referencesTableEntity);
    }

    //@Override
    public void removeContactGroup(int idC, int idG) {
        referencesTableDAO.delete(idC,idG);
    }

    //@Override
    public int login(String login, String password) {
        UsersEntity usersEntity = usersDAO.getUser(login,password);
        if (usersEntity!=null){
            return usersEntity.getId();
        }
        return 0;
    }
}
