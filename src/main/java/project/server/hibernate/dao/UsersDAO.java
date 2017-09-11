package project.server.hibernate.dao;

import project.server.hibernate.entities.UsersEntity;

import java.util.List;

public interface UsersDAO {

    int getId(String login, String password);

    UsersEntity getUser(String login, String password);

    void save(UsersEntity entity);

    List<UsersEntity> findAll();

    UsersEntity findById(int id);

    void deleteById(int id);

    void updateUser(UsersEntity entity);

    UsersEntity findByUsername(String username);

    int getUser_id();
}
