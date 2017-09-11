package project.server.hibernate.servlet;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import project.server.factory.EntityFactory;
import project.server.hibernate.servlet.services.HibernateContactServiceImpl;
import project.server.hibernate.servlet.services.HibernateGroupServiceImpl;
import project.server.model.jdbc.data.Contact;
import project.server.model.jdbc.data.Group;
import project.server.model.jdbc.services.AbstractContactService;
import project.server.model.jdbc.services.AbstractGroupService;
import project.server.model.jdbc.services.ContactService;
import project.server.model.jdbc.services.GroupService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

public class MyServlet extends HttpServlet {

//    AbstractContactService contactService = ContactService.getInstance();
//    AbstractGroupService groupService = GroupService.getInstance();

//    ApplicationContext ctx =
//            WebApplicationContextUtils.getRequiredWebApplicationContext(
//                    this.getServletContext());

    ApplicationContext ctx;

    String login;
    String password;
    Integer user_id;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(
                        this.getServletContext());
        HibernateContactServiceImpl contactService = (HibernateContactServiceImpl)ctx.getBean("servletContactsService");
        HibernateGroupServiceImpl groupService = (HibernateGroupServiceImpl)ctx.getBean("servletGroupService");

        login = req.getParameter("login");
        password = req.getParameter("password");
        System.out.println(login);
        System.out.println(password);

        user_id = contactService.login(login,password);

        resp.setContentType("text/html");
        req.setCharacterEncoding("UTF-8");

        if(user_id > 0){
            resp.getWriter().println(menuPage(""));
        }
        else {
            resp.getWriter().println("<!DOCTYPE html>\n" +
                    "<html lang=\"en\">\n" +
                    "<head>\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <title>Login</title>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "Wrong login or password" +
                    "<form id=\"login\" action=\"/MyServlet\" method=\"post\">\n" +
                    "    <p> <input type=\"text\" name=\"login\"></p>\n" +
                    "    <p><input type=\"text\" name=\"password\"></p>\n" +
                    "    <input type=\"submit\" id = \"driver\" value=\"Login\" />\n" +
                    "</form>\n" +
                    "</body>\n" +
                    "</html>");
        }


    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        HibernateContactServiceImpl contactService = (HibernateContactServiceImpl)ctx.getBean("servletContactsService");
        HibernateGroupServiceImpl groupService = (HibernateGroupServiceImpl)ctx.getBean("servletGroupService");

        if(req.getParameter("comand").equals("o")){

            resp.getWriter().println(menuPage(""));

        }

        if(req.getParameter("comand").equals("a")){
            resp.getWriter().println("<!DOCTYPE html>\n" +
                    "<html lang=\"en\">\n" +
                    "<head>\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <title>Add Contact</title>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "<form id=\"login\" action=\"/MyServlet\" method=\"get\">\n" +
                    "    <p>First Name: <input type=\"text\" name=\"fn\"></p>\n" +
                    "    <p>Last Name: <input type=\"text\" name=\"ln\"></p>\n" +
                    "    <p>Number: <input type=\"text\" name=\"num\"></p>\n" +
                    "    <input type=\"submit\" id = \"driver\" value=\"Save\" />\n" +
                    "    <input type=\"hidden\" id=\"command\" name=\"comand\" value=\"aa\" />\n" +
                    "</form>\n" +
                    "</body>\n" +
                    "</html>");
        }

        if(req.getParameter("comand").equals("aa")){
            try {
                contactService.save((Contact) EntityFactory.getEntity(
                        req.getParameter("fn"),req.getParameter("ln"),req.getParameter("num")),user_id);
                resp.getWriter().println(menuPage(""));

            } catch (Exception e) {
                resp.getWriter().println(menuPage("Enter correct data"));
                e.printStackTrace();
            }

        }

        if(req.getParameter("comand").equals("b")){
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("<!DOCTYPE html>\n" +
                    "<html lang=\"en\">\n" +
                    "<head>\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <title>Update Contact</title>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "<form id=\"menu\" action=\"/MyServlet\" method=\"get\">\n");

            try {
                Set<Contact> contactSet = contactService.getAll(user_id);
                for(Contact contact:contactSet) {
                    stringBuffer.append("<p> <input type=\"submit\" value=\"" + contact.getFirstName() + " " +
                     contact.getLastName() + " " + contact.getNumber() +
                            "\" onclick=\"document.getElementById('command').value = 'ba'; document.getElementById('user').value = '" +
                            contact.getId() + "'; document.myform.submit(); \"></p>\n");
                }

                stringBuffer.append("<input type=\"hidden\" id=\"command\" name=\"comand\" value=\"\" />\n" +
                        "<input type=\"hidden\" id=\"user\" name=\"user\" value=\"\" />\n" +
                        "</form>\n" +
                        "</body>\n" +
                        "</html>");
                resp.getWriter().println(stringBuffer.toString());
            } catch (Exception e) {
                e.printStackTrace();
                resp.getWriter().println(menuPage("Server Error"));
            }
        }

        if(req.getParameter("comand").equals("ba")){
            resp.getWriter().println("<!DOCTYPE html>\n" +
                    "<html lang=\"en\">\n" +
                    "<head>\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <title>Add Contact</title>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "<form id=\"login\" action=\"/MyServlet\" method=\"get\">\n" +
                    "    <p>First Name: <input type=\"text\" name=\"fn\"></p>\n" +
                    "    <p>Last Name: <input type=\"text\" name=\"ln\"></p>\n" +
                    "    <p>Number: <input type=\"text\" name=\"num\"></p>\n" +
                    "    <input type=\"submit\" id = \"driver\" value=\"Save\" />\n" +
                    "    <input type=\"hidden\" id=\"command\" name=\"comand\" value=\"bb\" />\n" +
                    "    <input type=\"hidden\" id=\"user\" name=\"user\" value=\"" + req.getParameter("user") + "\" />\n" +
                    "</form>\n" +
                    "</body>\n" +
                    "</html>");
        }

        if(req.getParameter("comand").equals("bb")){
            try {
                contactService.update((Contact)EntityFactory.getEntity(req.getParameter("fn"),
                        req.getParameter("ln"),req.getParameter("num")),
                        Integer.valueOf(req.getParameter("user")),user_id);

            } catch (Exception e) {
                e.printStackTrace();
            }
            resp.getWriter().println(menuPage(""));
        }

        if(req.getParameter("comand").equals("c")){
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("<!DOCTYPE html>\n" +
                    "<html lang=\"en\">\n" +
                    "<head>\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <title>Delete Contact</title>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "<form id=\"menu\" action=\"/MyServlet\" method=\"get\">\n");

            try {
                Set<Contact> contactSet = contactService.getAll(user_id);
                for(Contact contact:contactSet) {
                    stringBuffer.append("<p> <input type=\"submit\" value=\"" + contact.getFirstName() + " " +
                            contact.getLastName() + " " + contact.getNumber() +
                            "\" onclick=\"document.getElementById('command').value = 'ca'; document.getElementById('user').value = '" +
                            contact.getId() + "'; document.myform.submit(); \"></p>\n");
                }

                stringBuffer.append("<input type=\"hidden\" id=\"command\" name=\"comand\" value=\"\" />\n" +
                        "<input type=\"hidden\" id=\"user\" name=\"user\" value=\"\" />\n" +
                        "</form>\n" +
                        "</body>\n" +
                        "</html>");
                resp.getWriter().println(stringBuffer.toString());
            } catch (Exception e) {
                e.printStackTrace();
                resp.getWriter().println(menuPage("Server Error"));
            }
        }

        if(req.getParameter("comand").equals("ca")){
            try {
                contactService.removeById(Integer.valueOf(req.getParameter("user")));
                resp.getWriter().println(menuPage(""));
            } catch (Exception e) {
                e.printStackTrace();
                resp.getWriter().println(menuPage("Server Error"));
            }
        }

        if(req.getParameter("comand").equals("d")){
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("<!DOCTYPE html>\n" +
                    "<html lang=\"en\">\n" +
                    "<head>\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <title>Choise Contact</title>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "<form id=\"menu\" action=\"/MyServlet\" method=\"get\">\n");

            try {
                Set<Contact> contactSet = contactService.getAll(user_id);
                for(Contact contact:contactSet) {
                    stringBuffer.append("<p> <input type=\"submit\" value=\"" + contact.getFirstName() + " " +
                            contact.getLastName() + " " + contact.getNumber() +
                            "\" onclick=\"document.getElementById('command').value = 'da'; document.getElementById('user').value = '" +
                            contact.getId() + "'; document.myform.submit(); \"></p>\n");
                }

                stringBuffer.append("<input type=\"hidden\" id=\"command\" name=\"comand\" value=\"\" />\n" +
                        "<input type=\"hidden\" id=\"user\" name=\"user\" value=\"\" />\n" +
                        "</form>\n" +
                        "</body>\n" +
                        "</html>");
                resp.getWriter().println(stringBuffer.toString());
            } catch (Exception e) {
                e.printStackTrace();
                resp.getWriter().println(menuPage("Server Error"));
            }
        }

        if(req.getParameter("comand").equals("da")){
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("<!DOCTYPE html>\n" +
                    "<html lang=\"en\">\n" +
                    "<head>\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <title>Choise Contact</title>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "<form id=\"menu\" action=\"/MyServlet\" method=\"get\">\n");

            try {
                Set<Group> groupSet = groupService.getAll(user_id);
                for(Group group:groupSet) {
                    stringBuffer.append("<p> <input type=\"submit\" value=\"" + group.getName() +
                            "\" onclick=\"document.getElementById('command').value = 'db'; document.getElementById('group').value = '" +
                            group.getId() + "'; document.myform.submit(); \"></p>\n");
                }

                stringBuffer.append("<input type=\"hidden\" id=\"command\" name=\"comand\" value=\"\" />\n" +
                        "<input type=\"hidden\" id=\"group\" name=\"group\" value=\"\" />\n" +
                        "<input type=\"hidden\" id=\"user\" name=\"user\" value=\"" + req.getParameter("user") + "\" />\n" +
                        "</form>\n" +
                        "</body>\n" +
                        "</html>");
                resp.getWriter().println(stringBuffer.toString());
            } catch (Exception e) {
                e.printStackTrace();
                resp.getWriter().println(menuPage("Server Error"));
            }


        }

        if(req.getParameter("comand").equals("db")){
            try {
                contactService.addContactGroup(Integer.valueOf(req.getParameter("user")),Integer.valueOf(req.getParameter("group")));
                resp.getWriter().println(menuPage(""));
            } catch (Exception e) {
                e.printStackTrace();
                resp.getWriter().println(menuPage("Server Error"));
            }
        }


        if(req.getParameter("comand").equals("e")){
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("<!DOCTYPE html>\n" +
                    "<html lang=\"en\">\n" +
                    "<head>\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <title>Choise Contact</title>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "<form id=\"menu\" action=\"/MyServlet\" method=\"get\">\n");

            try {
                Set<Contact> contactSet = contactService.getAll(user_id);
                for(Contact contact:contactSet) {
                    stringBuffer.append("<p> <input type=\"submit\" value=\"" + contact.getFirstName() + " " +
                            contact.getLastName() + " " + contact.getNumber() +
                            "\" onclick=\"document.getElementById('command').value = 'ea'; document.getElementById('user').value = '" +
                            contact.getId() + "'; document.myform.submit(); \"></p>\n");
                }

                stringBuffer.append("<input type=\"hidden\" id=\"command\" name=\"comand\" value=\"\" />\n" +
                        "<input type=\"hidden\" id=\"user\" name=\"user\" value=\"\" />\n" +
                        "</form>\n" +
                        "</body>\n" +
                        "</html>");
                resp.getWriter().println(stringBuffer.toString());
            } catch (Exception e) {
                e.printStackTrace();
                resp.getWriter().println(menuPage("Server Error"));
            }
        }

        if(req.getParameter("comand").equals("ea")){
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("<!DOCTYPE html>\n" +
                    "<html lang=\"en\">\n" +
                    "<head>\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <title>Choise Contact</title>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "<form id=\"menu\" action=\"/MyServlet\" method=\"get\">\n");

            try {
                Set<Group> groupSet = groupService.getAll(user_id);
                Contact contact = contactService.getById(Integer.valueOf(req.getParameter("user")));
                for(Group group:groupSet) {
                    if(contact.containGroup(group.getId())) {
                        stringBuffer.append("<p> <input type=\"submit\" value=\"" + group.getName() +
                                "\" onclick=\"document.getElementById('command').value = 'eb'; document.getElementById('group').value = '" +
                                group.getId() + "'; document.myform.submit(); \"></p>\n");
                    }
                }

                stringBuffer.append("<input type=\"hidden\" id=\"command\" name=\"comand\" value=\"\" />\n" +
                        "<input type=\"hidden\" id=\"group\" name=\"group\" value=\"\" />\n" +
                        "<input type=\"hidden\" id=\"user\" name=\"user\" value=\"" + req.getParameter("user") + "\" />\n" +
                        "</form>\n" +
                        "</body>\n" +
                        "</html>");
                resp.getWriter().println(stringBuffer.toString());
            } catch (Exception e) {
                e.printStackTrace();
                resp.getWriter().println(menuPage("Server Error"));
            }
        }

        if(req.getParameter("comand").equals("eb")){
            try {
                contactService.removeContactGroup(Integer.valueOf(req.getParameter("user")),Integer.valueOf(req.getParameter("group")));
                resp.getWriter().println(menuPage(""));
            } catch (Exception e) {
                e.printStackTrace();
                resp.getWriter().println(menuPage("Server Error"));
            }
        }


        if(req.getParameter("comand").equals("f")){
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("<!DOCTYPE html>\n" +
                    "<html lang=\"en\">\n" +
                    "<head>\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <title>Contacts</title>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "<form id=\"menu\" action=\"/MyServlet\" method=\"get\">\n");

            try {
                Set<Contact> contactSet = contactService.getAll(user_id);
                for(Contact contact:contactSet) {
                    stringBuffer.append("<p>" + contact.getFirstName() + " " +
                            contact.getLastName() + " " + contact.getNumber() + " "
                            );
                    if(!contact.getGroupId().isEmpty()) {
                        for (Integer i :contact.getGroupId()){
                            Group group = groupService.getById(i);
                            if(group!=null) {
                                stringBuffer.append(group.getName() + " ");
                            }
                        }
                    }
                    stringBuffer.append("></p>\n");
                }

                stringBuffer.append("<input type=\"submit\" id=\"menu\" name=\"menu\" value=\"Menu\" />\n" +
                        "<input type=\"hidden\" id=\"command\" name=\"comand\" value=\"o\" />\n" +
                        "</form>\n" +
                        "</body>\n" +
                        "</html>");
                resp.getWriter().println(stringBuffer.toString());
            } catch (Exception e) {
                e.printStackTrace();
                resp.getWriter().println(menuPage("Server Error"));
            }
        }


        if(req.getParameter("comand").equals("h")){
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("<!DOCTYPE html>\n" +
                    "<html lang=\"en\">\n" +
                    "<head>\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <title>Groups</title>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "<form id=\"menu\" action=\"/MyServlet\" method=\"get\">\n");

            try {
                Set<Group> groupSet = groupService.getAll(user_id);
                for(Group group:groupSet) {
                    stringBuffer.append("<p> <input type=\"submit\" value=\"" + group.getName() +
                            "\" onclick=\"document.getElementById('command').value = 'g'; document.getElementById('group').value = '" +
                            group.getId() + "'; document.myform.submit(); \"></p>\n");
                }

                stringBuffer.append("<input type=\"hidden\" id=\"command\" name=\"comand\" value=\"\" />\n" +
                        "<input type=\"hidden\" id=\"group\" name=\"group\" value=\"\" />\n" +
                        "</form>\n" +
                        "</body>\n" +
                        "</html>");
                resp.getWriter().println(stringBuffer.toString());
            } catch (Exception e) {
                e.printStackTrace();
                resp.getWriter().println(menuPage("Server Error"));
            }
        }


        if(req.getParameter("comand").equals("g")){
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("<!DOCTYPE html>\n" +
                    "<html lang=\"en\">\n" +
                    "<head>\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <title>Contacts</title>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "<form id=\"menu\" action=\"/MyServlet\" method=\"get\">\n");

            try {
                Set<Contact> contactSet = contactService.getAll(user_id);
                for(Contact contact:contactSet) {
                    if(!contact.getGroupId().isEmpty()) {
                        if(contact.getGroupId().contains(Integer.valueOf(req.getParameter("group")))) {
                            stringBuffer.append("<p>" + contact.getFirstName() + " " +
                                    contact.getLastName() + " " + contact.getNumber() + " "
                            );
                            stringBuffer.append("></p>\n");
                        }
                    }
                }

                stringBuffer.append("<input type=\"submit\" id=\"menu\" name=\"menu\" value=\"Menu\" />\n" +
                        "<input type=\"hidden\" id=\"command\" name=\"comand\" value=\"o\" />\n" +
                        "</form>\n" +
                        "</body>\n" +
                        "</html>");
                resp.getWriter().println(stringBuffer.toString());
            } catch (Exception e) {
                e.printStackTrace();
                resp.getWriter().println(menuPage("Server Error"));
            }
        }

        if(req.getParameter("comand").equals("i")){
            resp.getWriter().println("<!DOCTYPE html>\n" +
                    "<html lang=\"en\">\n" +
                    "<head>\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <title>Add Contact</title>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "<form id=\"login\" action=\"/MyServlet\" method=\"get\">\n" +
                    "    <p>Name: <input type=\"text\" name=\"name\"></p>\n" +
                    "    <input type=\"submit\" id = \"driver\" value=\"Save\" />\n" +
                    "    <input type=\"hidden\" id=\"command\" name=\"comand\" value=\"ia\" />\n" +
                    "</form>\n" +
                    "</body>\n" +
                    "</html>");
        }

        if(req.getParameter("comand").equals("ia")){
            try {
                groupService.save((Group)EntityFactory.getEntity(req.getParameter("name")),user_id);
                resp.getWriter().println(menuPage(""));
            } catch (Exception e) {
                e.printStackTrace();
                resp.getWriter().println(menuPage("Server Error"));
            }

        }


        if(req.getParameter("comand").equals("j")){
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("<!DOCTYPE html>\n" +
                    "<html lang=\"en\">\n" +
                    "<head>\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <title>Groups</title>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "<form id=\"menu\" action=\"/MyServlet\" method=\"get\">\n");

            try {
                Set<Group> groupSet = groupService.getAll(user_id);
                for(Group group:groupSet) {
                    stringBuffer.append("<p> <input type=\"submit\" value=\"" + group.getName() +
                            "\" onclick=\"document.getElementById('command').value = 'ja'; document.getElementById('group').value = '" +
                            group.getId() + "'; document.myform.submit(); \"></p>\n");
                }

                stringBuffer.append("<input type=\"hidden\" id=\"command\" name=\"comand\" value=\"\" />\n" +
                        "<input type=\"hidden\" id=\"group\" name=\"group\" value=\"\" />\n" +
                        "</form>\n" +
                        "</body>\n" +
                        "</html>");
                resp.getWriter().println(stringBuffer.toString());
            } catch (Exception e) {
                e.printStackTrace();
                resp.getWriter().println(menuPage("Server Error"));
            }
        }


        if(req.getParameter("comand").equals("ja")) {

            try {
                groupService.removeById(Integer.valueOf(req.getParameter("group")));
                resp.getWriter().println(menuPage(""));
            } catch (Exception e) {
                e.printStackTrace();
                resp.getWriter().println(menuPage("Server Error"));
            }

        }


        if(req.getParameter("comand").equals("k")){
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("<!DOCTYPE html>\n" +
                    "<html lang=\"en\">\n" +
                    "<head>\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <title>Groups</title>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "<form id=\"menu\" action=\"/MyServlet\" method=\"get\">\n");

            try {
                Set<Group> groupSet = groupService.getAll(user_id);
                for(Group group:groupSet) {
                    stringBuffer.append("<p> <input type=\"submit\" value=\"" + group.getName() +
                            "\" onclick=\"document.getElementById('command').value = 'ka'; document.getElementById('group').value = '" +
                            group.getId() + "'; document.myform.submit(); \"></p>\n");
                }

                stringBuffer.append("<input type=\"hidden\" id=\"command\" name=\"comand\" value=\"\" />\n" +
                        "<input type=\"hidden\" id=\"group\" name=\"group\" value=\"\" />\n" +
                        "</form>\n" +
                        "</body>\n" +
                        "</html>");
                resp.getWriter().println(stringBuffer.toString());
            } catch (Exception e) {
                e.printStackTrace();
                resp.getWriter().println(menuPage("Server Error"));
            }
        }


        if(req.getParameter("comand").equals("ka")){
            resp.getWriter().println("<!DOCTYPE html>\n" +
                    "<html lang=\"en\">\n" +
                    "<head>\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <title>Add Contact</title>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "<form id=\"login\" action=\"/MyServlet\" method=\"get\">\n" +
                    "    <p>Name: <input type=\"text\" name=\"name\"></p>\n" +
                    "    <input type=\"submit\" id = \"driver\" value=\"Save\" />\n" +
                    "    <input type=\"hidden\" id=\"command\" name=\"comand\" value=\"kb\" />\n" +
                    "    <input type=\"hidden\" id=\"group\" name=\"group\" value=\"" + req.getParameter("group") + "\" />\n" +
                    "</form>\n" +
                    "</body>\n" +
                    "</html>");
        }

        if(req.getParameter("comand").equals("kb")){
            try {
                groupService.update((Group)EntityFactory.getEntity(req.getParameter("name")),Integer.valueOf(req.getParameter("group")),user_id);
                resp.getWriter().println(menuPage(""));
            } catch (Exception e) {
                e.printStackTrace();
                resp.getWriter().println(menuPage("Server Error"));
            }
        }




    }

    private String menuPage(String text){
        return "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Menu</title>\n" +
                "</head>\n" +
                "<body>\n" + text +
                "<form id=\"menu\" action=\"/MyServlet\" method=\"get\">\n" +
                "    <p> <input type=\"submit\" value=\"Add contact\" onclick=\"document.getElementById('command').value = 'a'; document.myform.submit(); \"></p>\n" +
                "    <p> <input type=\"submit\" value=\"Update contact\" onclick=\"document.getElementById('command').value = 'b'; document.myform.submit(); \"></p>\n" +
                "    <p> <input type=\"submit\" value=\"Delete contact\" onclick=\"document.getElementById('command').value = 'c'; document.myform.submit(); \"></p>\n" +
                "    <p> <input type=\"submit\" value=\"Add group to contact\" onclick=\"document.getElementById('command').value = 'd'; document.myform.submit(); \"></p>\n" +
                "    <p> <input type=\"submit\" value=\"Remove group from contact\" onclick=\"document.getElementById('command').value = 'e'; document.myform.submit(); \"></p>\n" +
                "    <p> <input type=\"submit\" value=\"Show contacts\" onclick=\"document.getElementById('command').value = 'f'; document.myform.submit(); \"></p>\n" +
                //"    <p> <input type=\"submit\" value=\"Просмотр контактов определенной группы\" onclick=\"document.getElementById('command').value = 'g'; document.myform.submit(); \"></p>\n" +
                "    <p> <input type=\"submit\" value=\"Show groups\" onclick=\"document.getElementById('command').value = 'h'; document.myform.submit(); \"></p>\n" +
                "    <p> <input type=\"submit\" value=\"Add group\" onclick=\"document.getElementById('command').value = 'i'; document.myform.submit(); \"></p>\n" +
                "    <p> <input type=\"submit\" value=\"Delete group\" onclick=\"document.getElementById('command').value = 'j'; document.myform.submit(); \"></p>\n" +
                "    <p> <input type=\"submit\" value=\"Update group\" onclick=\"document.getElementById('command').value = 'k'; document.myform.submit(); \"></p>\n" +
                "\n" +
                "    <input type=\"hidden\" id=\"command\" name=\"comand\" value=\"\" />\n" +
                "</form>\n" +
                "</body>\n" +
                "</html>";
    }
}
