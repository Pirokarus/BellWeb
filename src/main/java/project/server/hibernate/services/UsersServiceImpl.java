package project.server.hibernate.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.server.hibernate.dao.UsersDAO;
import project.server.hibernate.entities.UsersEntity;
import java.util.List;

@Service("usersService")
@Transactional
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersDAO usersDAO;

    @Override
    public int getId(String login, String password) {
        return usersDAO.getId(login, password);
    }

    @Override
    public UsersEntity getUser(String login, String password) {
        return usersDAO.getUser(login, password);
    }

    @Override
    public void save(UsersEntity entity) {
        usersDAO.save(entity);
    }

    @Override
    public List<UsersEntity> findAll() {
        return usersDAO.findAll();
    }

    @Override
    public UsersEntity findById(int id) {
        return usersDAO.findById(id);
    }

    @Override
    public void deleteById(int id) {
        usersDAO.deleteById(id);
    }

    @Override
    public void updateUser(UsersEntity entity) {
        usersDAO.updateUser(entity);
    }
}
