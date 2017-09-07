package project.server.hibernate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import project.server.hibernate.entities.ContactsEntity;
import project.server.hibernate.entities.GroupsEntity;
import project.server.hibernate.entities.ReferencesTableEntity;
import project.server.hibernate.entities.UsersEntity;
import project.server.hibernate.services.ContactsService;
import project.server.hibernate.services.GroupsService;
import project.server.hibernate.services.ReferencesTableService;
import project.server.hibernate.services.UsersService;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MyController {

    @Autowired
    UsersService usersService;

    @Autowired
    ContactsService contactsService;

    @Autowired
    GroupsService groupsService;

    @Autowired
    ReferencesTableService referencesTableService;


    @RequestMapping(value = "login", method = RequestMethod.POST)
    public void login(@RequestParam(value = "login")String login, @RequestParam(value = "password")String password){

    }


    @RequestMapping(value = "getAllContacts")
    public List<ContactsEntity> getAllContacts(){
        UsersEntity user = usersService.findById(1);
        return contactsService.findAll(user);
    }

    @RequestMapping(value = "addContact")
    public void addContact(@RequestParam(value = "contact")ContactsEntity contactsEntity){
        contactsService.save(contactsEntity);
    }

    @RequestMapping(value = "updateContact")
    public void updateContact(@RequestParam(value = "contact")ContactsEntity contactsEntity){
        contactsService.updateContact(contactsEntity);
    }

    @RequestMapping(value = "deleteContact")
    public void deleteContact(@RequestParam(value = "id")int id){
        contactsService.deleteById(id);
    }

    @RequestMapping(value = "addGroupToContact")
    public void addGroupToContact(@RequestParam(value = "idC")int idC, @RequestParam(value = "idG")int idG){
        ReferencesTableEntity referencesTableEntity = new ReferencesTableEntity();
        referencesTableEntity.setContactsByContactId(contactsService.findById(idC));
        referencesTableEntity.setGroupsByGroupId(groupsService.findById(idG));
        referencesTableService.save(referencesTableEntity);
    }

    @RequestMapping(value = "removeGroupFromContact")
    public void removeGroupFromContact(@RequestParam(value = "idC")int idC, @RequestParam(value = "idG")int idG){
        referencesTableService.delete(idC,idG);
    }

    @RequestMapping(value = "getGroupsContacts")
    public List<ContactsEntity> getGroupsContacts(@RequestParam(value = "idG")int idG){
        List<ContactsEntity> list = new ArrayList<>();
        List<ReferencesTableEntity> referencesList = referencesTableService.findAllByGroupId(idG);
        for (ReferencesTableEntity referencesTableEntity:referencesList){
            list.add(referencesTableEntity.getContactsByContactId());
        }

        return list;
    }

    @RequestMapping(value = "getAllGroups")
    public List<GroupsEntity> getAllGroups(){
        UsersEntity user = usersService.findById(1);
        return groupsService.findAll(user);
    }

    @RequestMapping(value = "addGroup")
    public void addGroup(@RequestParam(value = "group")GroupsEntity groupsEntity){
        groupsService.save(groupsEntity);
    }

    @RequestMapping(value = "updateGroup")
    public void updateGroup(@RequestParam(value = "group")GroupsEntity groupsEntity){
        groupsService.updateGroup(groupsEntity);
    }

    @RequestMapping(value = "deleteGroup")
    public void deleteGroup(@RequestParam(value = "id")int id){
        groupsService.deleteById(id);
    }

}
