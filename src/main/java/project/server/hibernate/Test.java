package project.server.hibernate;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import project.server.hibernate.config.SpringConfiguration;


public class Test {

    public static void main(String[] args) {
        //AbstractApplicationContext context = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        //MyController myController = (MyController)context.getBean(MyController.class);
        //System.out.println(myController.getAllContacts());
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        System.out.println(bCryptPasswordEncoder.encode("user2"));
    }
}
