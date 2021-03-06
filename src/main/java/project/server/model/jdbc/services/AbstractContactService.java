package project.server.model.jdbc.services;

import project.server.model.jdbc.data.Contact;

import java.util.Set;

public interface AbstractContactService {
    /**
     * Метод охраняет контакт
     * @param contact
     * @throws Exception
     */
    void save(Contact contact, int user_id) throws Exception;

    /**
     * Метод удаляет контакт
     * @param contact
     * @throws Exception
     */
    //void remove(Contact contact) throws Exception;

    /**
     * Метод удаляет контакт по id
     * @param id
     * @throws Exception
     */
    void removeById(int id) throws Exception;

    /**
     * Метод обновляет контакт
     * @param contact
     * @param id
     * @throws Exception
     */
    void update(Contact contact, int id, int user_id) throws Exception;

    /**
     * Метод возвращает список всех контактов
     * @return
     * @throws Exception
     */
    Set<Contact> getAll(int user_id) throws Exception;

    /**
     * Метод возвращает контакт по id
     * @param id
     * @return
     * @throws Exception
     */
    Contact getById(int id) throws Exception;

    void addContactGroup(int idC, int idG);
    void removeContactGroup(int idC, int idG);
    int login(String login, String password);

}
