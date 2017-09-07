package project.server.hibernate.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import project.server.hibernate.entities.ContactsEntity;
import project.server.hibernate.entities.UsersEntity;

import java.util.List;

@Repository("contactsDAO")
public class ContactsDAOImpl extends AbstractDAO implements ContactsDAO {

    public void save(ContactsEntity contactsEntity) {
        persist(contactsEntity);
    }

    public List<ContactsEntity> findAll(UsersEntity user_id) {
        Criteria criteria = getSession().createCriteria(ContactsEntity.class);
        criteria.add(Restrictions.eq("user_id", user_id));
        return (List<ContactsEntity>)criteria.list();
    }

    public ContactsEntity findById(int id) {
        Criteria criteria = getSession().createCriteria(ContactsEntity.class);
        criteria.add(Restrictions.eq("id", id));
        return (ContactsEntity) criteria.uniqueResult();
    }

    public void deleteById(int id) {
        Criteria criteria = getSession().createCriteria(ContactsEntity.class);
        criteria.add(Restrictions.eq("id", id));
        delete((ContactsEntity)criteria.uniqueResult());
    }

    public void updateContact(ContactsEntity contactsEntity){
        update(contactsEntity);
    }
}
