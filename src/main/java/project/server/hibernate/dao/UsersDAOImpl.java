package project.server.hibernate.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import project.server.hibernate.entities.UsersEntity;

import java.util.List;

@Repository("usersDAO")
public class UsersDAOImpl extends AbstractDAO implements UsersDAO {

    @Override
    public int getId(String login, String password) {
        Criteria criteria = getSession().createCriteria(UsersEntity.class);
        criteria.add(Restrictions.eq("login", login));
        criteria.add(Restrictions.eq("password", password));
        int out;
        if(criteria.uniqueResult()==null){
            out = 0;
        }else{
            out = ((UsersEntity)criteria.uniqueResult()).getId();
        }
        return out;
    }

    @Override
    public UsersEntity getUser(String login, String password) {
        Criteria criteria = getSession().createCriteria(UsersEntity.class);
        criteria.add(Restrictions.eq("login", login));
        criteria.add(Restrictions.eq("password", password));
        return (UsersEntity)criteria.uniqueResult();
    }

    @Override
    public void save(UsersEntity entity) {
        persist(entity);
    }

    @Override
    public List<UsersEntity> findAll() {
        Criteria criteria = getSession().createCriteria(UsersEntity.class);
        return (List<UsersEntity>)criteria.list();
    }

    @Override
    public UsersEntity findById(int id) {
        Criteria criteria = getSession().createCriteria(UsersEntity.class);
        criteria.add(Restrictions.eq("id", id));
        return (UsersEntity) criteria.uniqueResult();
    }

    @Override
    public void deleteById(int id) {
        Criteria criteria = getSession().createCriteria(UsersEntity.class);
        criteria.add(Restrictions.eq("id", id));
        delete((UsersEntity)criteria.uniqueResult());
    }

    @Override
    public void updateUser(UsersEntity entity) {
        update(entity);
    }

    @Override
    public UsersEntity findByUsername(String username) {
        Criteria criteria = getSession().createCriteria(UsersEntity.class);
        criteria.add(Restrictions.eq("login", username));
        return (UsersEntity) criteria.uniqueResult();
    }
}
