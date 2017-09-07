package project.server.hibernate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import project.server.hibernate.config.SpringConfiguration;
import project.server.hibernate.services.ContactsService;
import project.server.hibernate.services.GroupsService;
import project.server.hibernate.services.UsersService;


public class Test {

    public static void main(String[] args) {
        AbstractApplicationContext context = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        MyController myController = (MyController)context.getBean(MyController.class);
        System.out.println(myController.getAllContacts());
    }
}
