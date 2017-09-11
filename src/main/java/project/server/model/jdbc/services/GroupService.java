package project.server.model.jdbc.services;


import org.xml.sax.SAXException;
import project.server.model.jdbc.dao.GroupDAO;
import project.server.model.jdbc.dao.jdbc.JdbcGroupDAO;
import project.server.model.jdbc.data.Group;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;
import java.util.*;

public class GroupService extends Observable implements AbstractGroupService {

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

    @Override
    public void remove(Group group) throws Exception {
        removeById(group.getId());
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
