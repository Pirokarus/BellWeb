package project.server.hibernate.dao;

import project.server.hibernate.entities.GroupsEntity;
import project.server.hibernate.entities.UsersEntity;

import java.util.List;

public interface GroupsDAO {

    void save(GroupsEntity entity);

    List<GroupsEntity> findAll(UsersEntity user_id);

    GroupsEntity findById(int id);

    void deleteById(int id);

    void updateGroup(GroupsEntity entity);
}
