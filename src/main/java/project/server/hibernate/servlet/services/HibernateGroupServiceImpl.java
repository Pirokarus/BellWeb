package project.server.hibernate.servlet.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.xml.sax.SAXException;
import project.server.hibernate.dao.GroupsDAO;
import project.server.hibernate.dao.UsersDAO;
import project.server.hibernate.entities.GroupsEntity;
import project.server.hibernate.entities.UsersEntity;
import project.server.model.jdbc.data.Group;
import project.server.model.jdbc.services.AbstractGroupService;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;


@Service("servletGroupService")
@Transactional
public class HibernateGroupServiceImpl /*implements AbstractGroupService*/ {

    @Autowired
    GroupsDAO groupsDAO;

    @Autowired
    UsersDAO usersDAO;

    //@Override
    public void save(Group group, int user_id) throws ParserConfigurationException, IOException, SAXException, TransformerException {
        UsersEntity usersEntity = usersDAO.findById(user_id);
        GroupsEntity groupsEntity = group.groupsEntity(usersEntity);
        groupsDAO.save(groupsEntity);
    }

    //@Override
    public void remove(Group group) throws ParserConfigurationException, IOException, SAXException, XPathExpressionException, TransformerException {
        groupsDAO.deleteById(group.getId());
    }

    //@Override
    public void removeById(int id) throws ParserConfigurationException, IOException, SAXException, XPathExpressionException, TransformerException {
        groupsDAO.deleteById(id);
    }

    //@Override
    public void update(Group group, int id, int user_id) throws ParserConfigurationException, IOException, SAXException, XPathExpressionException, TransformerException {
        UsersEntity usersEntity=usersDAO.findById(user_id);
        GroupsEntity groupsEntity = group.groupsEntity(usersEntity);
        groupsDAO.updateGroup(groupsEntity);
    }

    //@Override
    public Set<Group> getAll(int user_id) throws ParserConfigurationException, IOException, SAXException, XPathExpressionException {
        Set<Group> groups = new HashSet<>();
        for (GroupsEntity groupsEntity:groupsDAO.findAll(usersDAO.findById(user_id))){
            groups.add(new Group(groupsEntity));
        }
        return groups;
    }

    //@Override
    public Group getById(int id) throws ParserConfigurationException, IOException, SAXException, XPathExpressionException {
        return new Group(groupsDAO.findById(id));
    }
}
