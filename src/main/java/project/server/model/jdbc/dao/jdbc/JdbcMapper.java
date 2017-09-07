package project.server.model.jdbc.dao.jdbc;

import project.server.exceptions.MyNotPhoneNumberException;
import project.server.factory.EntityFactory;
import project.server.model.jdbc.data.Contact;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class JdbcMapper {
    public static Set<Contact> resultToContacts(ResultSet result, ResultSet result1) throws SQLException, MyNotPhoneNumberException {
        Set<Contact> contactSet = new HashSet<Contact>();
        ResultSet result2 = result1;
        while (result.next()) {

            Set<Integer> groupSet = new HashSet<Integer>();

            result1 = result2;
            while (result1.next()){
                if(result1.getInt("contact_id") == result.getInt("id")) {
                    groupSet.add(result1.getInt("group_id"));
                }
            }

            contactSet.add((Contact) EntityFactory.getEntity(result.getInt("id"),
                    result.getString("firstName"),result.getString("lastName"),
                    result.getString("number"),groupSet));
        }
        return contactSet;
    }
}
