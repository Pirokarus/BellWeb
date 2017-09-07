package project.server.hibernate.dao;

import org.hibernate.Criteria;
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
    public List<ReferencesTableEntity> findAllByGroupId(int groupId) {
        Criteria criteria = getSession().createCriteria(ReferencesTableEntity.class);
        criteria.add(Restrictions.eq("group_id", groupId));
        return (List<ReferencesTableEntity>)criteria.list();
    }

    @Override
    public List<ReferencesTableEntity> findAllByContactId(int contactId) {
        Criteria criteria = getSession().createCriteria(ReferencesTableEntity.class);
        criteria.add(Restrictions.eq("contact_id", contactId));
        return (List<ReferencesTableEntity>)criteria.list();
    }

    @Override
    public void delete(int contactId, int groupId) {
        Criteria criteria = getSession().createCriteria(ReferencesTableEntity.class);
        criteria.add(Restrictions.eq("contact_id", contactId));
        criteria.add(Restrictions.eq("group_id", groupId));
        delete((ReferencesTableEntity)criteria.uniqueResult());
    }
}
