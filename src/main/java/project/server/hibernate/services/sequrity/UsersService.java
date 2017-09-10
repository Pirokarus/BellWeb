package project.server.hibernate.services.sequrity;

import project.server.hibernate.entities.UsersEntity;

import java.util.List;

public interface UsersService {

    int getId(String login, String password);

    UsersEntity getUser(String login, String password);

    void save(UsersEntity entity);

    List<UsersEntity> findAll();

    UsersEntity findById(int id);

    void deleteById(int id);

    void updateUser(UsersEntity entity);

    UsersEntity findByUsername(String username);
}
