package project.server.hibernate.dao;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import project.server.hibernate.entities.ReferencesTableEntity;

import java.util.List;

@Repository("referencesTableDAO")
public class ReferencesTableDAOImpl extends AbstractDAO implements ReferencesTableDAO {
    @Override
    public void save(ReferencesTableEntity entity) {
        persist(entity);
    }

    @Override
    public List<ReferencesTableEntity> findAllByGroupId(int group_id) {
        /*
        Criteria criteria = getSession().createCriteria(ReferencesTableEntity.class);
        criteria.add(Restrictions.eq("group_id.id", group_id));
        return (List<ReferencesTableEntity>)criteria.list();*/

        Query result = getSession().createQuery("FROM ReferencesTableEntity r WHERE r.groupsEntity.id = " + group_id);
        return (List<ReferencesTableEntity>)result.list();
    }

    @Override
    public List<ReferencesTableEntity> findAllByContactId(int contact_id) {
//        Criteria criteria = getSession().createCriteria(ReferencesTableEntity.class);
//        criteria.add(Restrictions.eq("contact_id.id", contact_id));
//        return (List<ReferencesTableEntity>)criteria.list();

        Query result = getSession().createQuery("FROM ReferencesTableEntity r WHERE r.contactsEntity.id = " + contact_id);
        return (List<ReferencesTableEntity>)result.list();
    }

    @Override
    public void delete(int contactId, int groupId) {
        Query result = getSession().createQuery("FROM ReferencesTableEntity r " +
                "WHERE r.groupsEntity.id = "+ groupId + " AND r.contactsEntity.id = " + contactId );
        delete((ReferencesTableEntity)result.uniqueResult());
    }


}
