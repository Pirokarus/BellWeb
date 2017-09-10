package project.server.hibernate.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import project.server.hibernate.entities.RolesEntity;

import java.util.List;

@Repository("rolesDAO")
public class RoleDAOImpl extends AbstractDAO implements RoleDAO {
    @Override
    public void save(RolesEntity entity) {
        persist(entity);
    }

    @Override
    public List<RolesEntity> findAll() {
        Criteria criteria = getSession().createCriteria(RolesEntity.class);
        return (List<RolesEntity>)criteria.list();
    }

    @Override
    public RolesEntity findById(int id) {
        Criteria criteria = getSession().createCriteria(RolesEntity.class);
        criteria.add(Restrictions.eq("id", id));
        return (RolesEntity) criteria.uniqueResult();
    }

    @Override
    public void deleteById(int id) {
        Criteria criteria = getSession().createCriteria(RolesEntity.class);
        criteria.add(Restrictions.eq("id", id));
        delete((RolesEntity)criteria.uniqueResult());
    }

    @Override
    public void updateRole(RolesEntity entity) {
        update(entity);
    }
}
