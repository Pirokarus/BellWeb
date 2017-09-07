package project.server.hibernate.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import project.server.hibernate.entities.GroupsEntity;
import project.server.hibernate.entities.UsersEntity;

import java.util.List;

@Repository("groupsDAO")
public class GroupsDAOImpl extends AbstractDAO implements GroupsDAO{
    @Override
    public void save(GroupsEntity entity) {
        persist(entity);
    }

    @Override
    public List<GroupsEntity> findAll(UsersEntity user_id) {
        Criteria criteria = getSession().createCriteria(GroupsEntity.class);
        criteria.add(Restrictions.eq("user_id", user_id));
        return (List<GroupsEntity>)criteria.list();
    }

    @Override
    public GroupsEntity findById(int id) {
        Criteria criteria = getSession().createCriteria(GroupsEntity.class);
        criteria.add(Restrictions.eq("id", id));
        return (GroupsEntity) criteria.uniqueResult();
    }

    @Override
    public void deleteById(int id) {
        Criteria criteria = getSession().createCriteria(GroupsEntity.class);
        criteria.add(Restrictions.eq("id", id));
        delete((GroupsEntity)criteria.uniqueResult());
    }

    @Override
    public void updateGroup(GroupsEntity entity) {
        update(entity);
    }
}
