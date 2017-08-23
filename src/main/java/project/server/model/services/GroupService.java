package project.server.model.services;


import project.server.model.dao.GroupDAO;
import project.server.model.dao.jdbc.JdbcGroupDAO;
import project.server.model.data.Group;

import project.server.model.dao.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class GroupService extends Observable implements GroupDAO {

    private static volatile GroupService service;
    private Vector<Observer> observers = new Vector<Observer>();

    private GroupService(){
    }

    public synchronized static GroupService getInstance(){
        GroupService groupService = service;
        if(groupService==null){
            synchronized (GroupService.class){
                groupService = service;
                if(groupService==null){
                    service = groupService = new GroupService();
                }
            }
        }
        return service;
    }

    private GroupDAO groupDAO = new JdbcGroupDAO();

    //@Override
    public synchronized void save(Group group, int user_id) throws Exception {
        groupDAO.save(group,user_id);
        updateEvent();
    }
/*
    @Override
    public void remove(Group group) throws Exception {
        groupDAO.remove(group);
        updateEvent();
    }*/

    //@Override
    public synchronized void removeById(int id) throws Exception {
        groupDAO.removeById(id);
        updateEvent();
    }

    //@Override
    public synchronized void update(Group group, int id, int user_id) throws Exception {
        groupDAO.update(group,id,user_id);
        updateEvent();
    }

    //@Override
    public synchronized Set<Group> getAll(int user_id) throws Exception {
        return groupDAO.getAll(user_id);
    }

    //@Override
    public synchronized Group getById(int id) throws Exception {
        return groupDAO.getById(id);
    }

    public synchronized void updateEvent(){
        for (Observer outlet:this.observers){
            outlet.update(this,true);
        }
    }

    public synchronized void register(Observer outlet) {

        observers.add(outlet);

    }
}
