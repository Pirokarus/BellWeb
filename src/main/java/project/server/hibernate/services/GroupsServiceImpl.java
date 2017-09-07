package project.server.hibernate.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.server.hibernate.dao.GroupsDAO;
import project.server.hibernate.entities.GroupsEntity;
import project.server.hibernate.entities.UsersEntity;


import java.util.List;

@Service("groupsService")
@Transactional
public class GroupsServiceImpl implements GroupsService {

    @Autowired
    private GroupsDAO groupsDAO;

    @Override
    public void save(GroupsEntity entity) {
        groupsDAO.save(entity);
    }

    @Override
    public List<GroupsEntity> findAll(UsersEntity user_id) {
        return groupsDAO.findAll(user_id);
    }

    @Override
    public GroupsEntity findById(int id) {
        return groupsDAO.findById(id);
    }

    @Override
    public void deleteById(int id) {
        groupsDAO.deleteById(id);
    }

    @Override
    public void updateGroup(GroupsEntity entity) {
        groupsDAO.updateGroup(entity);
    }
}
