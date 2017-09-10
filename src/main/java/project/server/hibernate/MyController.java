package project.server.hibernate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import project.server.hibernate.containers.Contact;
import project.server.hibernate.containers.Group;
import project.server.hibernate.entities.ContactsEntity;
import project.server.hibernate.entities.GroupsEntity;
import project.server.hibernate.entities.ReferencesTableEntity;
import project.server.hibernate.entities.UsersEntity;
import project.server.hibernate.services.ContactsService;
import project.server.hibernate.services.GroupsService;
import project.server.hibernate.services.ReferencesTableService;
import project.server.hibernate.services.sequrity.SecurityService;
import project.server.hibernate.services.sequrity.UsersService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MyController {

    @Autowired
    UsersService usersService;

    @Autowired
    ContactsService contactsService;

    @Autowired
    GroupsService groupsService;

    @Autowired
    ReferencesTableService referencesTableService;

    @Autowired
    private SecurityService securityService;

    UsersEntity user;


    @RequestMapping(value = "login", method = RequestMethod.POST)
    public List<Contact> login(@RequestParam(value = "login")String login, @RequestParam(value = "password")String password){
        securityService.autoLogin(login,password);
        user = usersService.findByUsername(login);
        return getAllContacts();
    }

    @ResponseBody
    @RequestMapping(value = "/a", method = RequestMethod.GET, produces = "application/json")
    public List<ContactsEntity> getC() {
        return contactsService.findAll(usersService.findById(1));
    }


    @ResponseBody
    @RequestMapping(value = "getAllContacts", method = RequestMethod.GET, produces = "application/json")
    public List<Contact> getAllContacts(){
        List<Contact> contactList = new ArrayList<>();
        for (ContactsEntity contactsEntity:contactsService.findAll(user)){
            contactList.add(new Contact(contactsEntity));
        }
        return contactList;
    }

    @ResponseBody
    @RequestMapping(value = "addContact", method = RequestMethod.POST)
    public void addContact(@RequestBody Contact contact){
        ContactsEntity contactsEntity = contact.contactsEntity();
        contactsEntity.setUser_id(user);
        contactsService.save(contactsEntity);
    }

    @ResponseBody
    @RequestMapping(value = "updateContact", method = RequestMethod.PUT)
    public void updateContact(@RequestBody Contact contact){
        ContactsEntity contactsEntity = contact.contactsEntity();
        contactsEntity.setUser_id(user);
        contactsService.updateContact(contactsEntity);
    }

    @ResponseBody
    @RequestMapping(value = "deleteContact", method = RequestMethod.DELETE)
    public void deleteContact(@RequestParam(value = "id")int id){
        contactsService.deleteById(id);
    }

    @ResponseBody
    @RequestMapping(value = "addGroupToContact", method = RequestMethod.POST)
    public void addGroupToContact(@RequestParam(value = "contactId")int idC,
                                  @RequestParam(value = "groupId")int idG){
        ReferencesTableEntity referencesTableEntity = new ReferencesTableEntity();
        referencesTableEntity.setContactsByContactId(contactsService.findById(idC));
        referencesTableEntity.setGroupsByGroupId(groupsService.findById(idG));
        referencesTableService.save(referencesTableEntity);
    }

    @ResponseBody
    @RequestMapping(value = "removeGroupFromContact", method = RequestMethod.DELETE)
    public void removeGroupFromContact(@RequestParam(value = "contactId")int idC,
                                       @RequestParam(value = "groupId")int idG){
        referencesTableService.delete(idC,idG);
    }

    @ResponseBody
    @RequestMapping(value = "getGroupsContacts", method = RequestMethod.GET, produces = "application/json")
    public List<Contact> getGroupsContacts(@RequestParam(value = "id")int id){
        List<Contact> list = new ArrayList<>();
        List<ReferencesTableEntity> referencesList = referencesTableService.findAllByGroupId(id);
        for (ReferencesTableEntity referencesTableEntity:referencesList){
            list.add(new Contact(referencesTableEntity.getContactsByContactId()));
        }

        return list;
    }

    @ResponseBody
    @RequestMapping(value = "getAllGroups", method = RequestMethod.GET, produces = "application/json")
    public List<Group> getAllGroups(){
        List<Group> groups = new ArrayList<>();
        for (GroupsEntity groupsEntity:groupsService.findAll(user)){
            groups.add(new Group(groupsEntity));
        }
        return groups;
    }

    @ResponseBody
    @RequestMapping(value = "addGroup", method = RequestMethod.POST)
    public void addGroup(@RequestBody Group group){
        GroupsEntity groupsEntity = group.groupsEntity();
        groupsEntity.setUser_id(user);
        groupsService.save(groupsEntity);
    }

    @ResponseBody
    @RequestMapping(value = "updateGroup", method = RequestMethod.PUT)
    public void updateGroup(@RequestBody Group group){
        GroupsEntity groupsEntity = group.groupsEntity();
        groupsEntity.setUser_id(user);
        groupsService.updateGroup(groupsEntity);
    }

    @ResponseBody
    @RequestMapping(value = "deleteGroup", method = RequestMethod.DELETE)
    public void deleteGroup(@RequestParam(value = "id") int id){
        groupsService.deleteById(id);
    }

}
