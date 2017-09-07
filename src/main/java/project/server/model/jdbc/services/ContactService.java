package project.server.model.jdbc.services;

import project.server.model.jdbc.dao.ContactDAO;
import project.server.model.jdbc.dao.DAOTypes;
import project.server.model.jdbc.dao.jdbc.JdbcContactDAO;
import project.server.model.jdbc.data.Contact;

import java.util.*;

public class ContactService extends Observable implements AbstractContactService {

    private static volatile ContactService service;
    private ArrayList<Observer> observers = new ArrayList<Observer>();

    private ContactService(){

            contactDAO = new JdbcContactDAO();

    }

    public synchronized static ContactService getInstance(){
        ContactService contactService = service;
        if(contactService==null){
            synchronized (ContactService.class){
                contactService = service;
                if(contactService==null){
                    service = new ContactService();
                }
            }
        }
        return service;
    }

    private ContactDAO contactDAO;


    public synchronized void save(Contact contact, int user_id) throws Exception {
        contactDAO.save(contact, user_id);
        updateEvent();
    }


    public synchronized void removeById(int id) throws Exception {
        contactDAO.removeById(id);
        updateEvent();
    }

    public synchronized void update(Contact contact, int id, int user_id) throws Exception {

        contactDAO.update(contact, id, user_id);
        updateEvent();
    }

    public synchronized Set<Contact> getAll(int user_id) throws Exception {
        return contactDAO.getAll(user_id);
    }

    public synchronized Contact getById(int id) throws Exception {
        return contactDAO.getById(id);
    }

    public synchronized void updateEvent(){
        for (Observer outlet:this.observers){
            outlet.update(this,true);
        }
    }

    public synchronized void register(Observer outlet) {

        observers.add(outlet);

    }

    public synchronized void addContactGroup(int idC, int idG) {
        contactDAO.addContactGroup(idC,idG);
        updateEvent();
    }

    public synchronized void removeContactGroup(int idC, int idG) {
        contactDAO.removeContactGroup(idC,idG);
        updateEvent();
    }

    public synchronized int login(String login, String password) {
        return contactDAO.login(login,password);
    }
}
