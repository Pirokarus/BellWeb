package project.server.hibernate.services;

import project.server.hibernate.entities.GroupsEntity;
import project.server.hibernate.entities.UsersEntity;

import java.util.List;

public interface GroupsService {

    void save(GroupsEntity entity);

    List<GroupsEntity> findAll(UsersEntity user_id);

    GroupsEntity findById(int id);

    void deleteById(int id);

    void updateGroup(GroupsEntity entity);
}
