package project.server.factory;

import project.server.model.dao.ContactDAO;
import project.server.model.dao.DAOTypes;
import project.server.model.dao.GroupDAO;
import project.server.model.dao.jdbc.JdbcContactDAO;
import project.server.model.dao.jdbc.JdbcGroupDAO;

public class DAOFactory {
    public static ContactDAO getContactDAO(DAOTypes type){
        switch (type){
            /*case DOM:
                return new DOMContactDAO();
            case JACKSON:
                return new JacksonContactDao();
            case SAX:
                return new SAXContactDAO();*/
            case JDBC:
                return new JdbcContactDAO();
        }
        return null;
    }

    public static GroupDAO getGroupDAO(DAOTypes type){
        switch (type){
            /*case DOM:
                return new DOMGroupDAO();
            case JACKSON:
                return new JacksonGroupDAO();
            case SAX:
                return new SAXGroupDAO();*/
            case JDBC:
                return new JdbcGroupDAO();
        }
        return null;
    }

}
