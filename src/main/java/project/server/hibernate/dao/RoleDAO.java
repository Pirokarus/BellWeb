package project.server.hibernate.dao;

import project.server.hibernate.entities.RolesEntity;

import java.util.List;

public interface RoleDAO {

    void save(RolesEntity entity);

    List<RolesEntity> findAll();

    RolesEntity findById(int id);

    void deleteById(int id);

    void updateRole(RolesEntity entity);


}
