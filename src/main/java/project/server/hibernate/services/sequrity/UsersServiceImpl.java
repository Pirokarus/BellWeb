package project.server.hibernate.services.sequrity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.server.hibernate.dao.RoleDAO;
import project.server.hibernate.dao.UsersDAO;
import project.server.hibernate.entities.RolesEntity;
import project.server.hibernate.entities.UsersEntity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service("usersService")
@Transactional
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersDAO usersDAO;

    @Autowired
    private RoleDAO roleDAO;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

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
        entity.setPassword(bCryptPasswordEncoder.encode(entity.getPassword()));
        Set<RolesEntity> roles = new HashSet<>();
        roles.add(roleDAO.findById(1));
        entity.setRoles(roles);
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

    @Override
    public UsersEntity findByUsername(String username) {
        return usersDAO.findByUsername(username);
    }
}
