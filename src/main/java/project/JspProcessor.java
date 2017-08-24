package project;

import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashSet;
import java.util.Set;

public class JspProcessor {
    public static int numberOfUsers(){
        Connection connection = null;
        InitialContext initialContext = null;
        DataSource dataSource = null;
        int res = 0;

        try {
            initialContext = new InitialContext();
            dataSource = (DataSource) initialContext.lookup("java:/comp/env/jdbc/postgres");
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = null;
            preparedStatement = connection.prepareStatement(
                    "SELECT COUNT (*) FROM users");
            ResultSet result = preparedStatement.executeQuery();
            result.next();
            res = result.getInt("count");
        }catch (Exception e){
            e.printStackTrace();
        }
        return res;
    }

    public static double avgContactsInGroup(){
        Connection connection = null;
        InitialContext initialContext = null;
        DataSource dataSource = null;
        double res = 0;

        try {
            initialContext = new InitialContext();
            dataSource = (DataSource) initialContext.lookup("java:/comp/env/jdbc/postgres");
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = null;
            preparedStatement = connection.prepareStatement(
                    "SELECT avg(foo)  FROM (select count(*) as foo from references_table group by group_id) as t");
            ResultSet result = preparedStatement.executeQuery();
            result.next();
            res = result.getDouble("avg");
        }catch (Exception e){
            e.printStackTrace();
        }
        return  res;
    }

    public static double avgNumUsersContacts(){
        Connection connection = null;
        InitialContext initialContext = null;
        DataSource dataSource = null;
        double res = 0;

        try {
            initialContext = new InitialContext();
            dataSource = (DataSource) initialContext.lookup("java:/comp/env/jdbc/postgres");
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = null;
            preparedStatement = connection.prepareStatement(
                    "SELECT avg(foo)  FROM (select count(*) as foo from contacts group by user_id) as t");
            ResultSet result = preparedStatement.executeQuery();
            result.next();
            res = result.getDouble("avg");
        }catch (Exception e){
            e.printStackTrace();
        }
        return res;
    }

    public static Set<String> dreamUsers(){
        Connection connection = null;
        InitialContext initialContext = null;
        DataSource dataSource = null;
        Set<String> res = new HashSet<String>();

        try {
            initialContext = new InitialContext();
            dataSource = (DataSource) initialContext.lookup("java:/comp/env/jdbc/postgres");
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = null;
            preparedStatement = connection.prepareStatement(
                    "SELECT u.login from users u join(select user_id from contacts group by user_id having count(*)<10) c on u.id=c.user_id");
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                res.add(result.getString("login"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return res;
    }

    public static int numberOfUsersContacts(int user_id){

        Connection connection = null;
        InitialContext initialContext = null;
        DataSource dataSource = null;
        int res = 0;

        try {
            initialContext = new InitialContext();
            dataSource = (DataSource) initialContext.lookup("java:/comp/env/jdbc/postgres");
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = null;
            preparedStatement = connection.prepareStatement(
                    "SELECT COUNT (*) FROM contacts WHERE user_id = ?");
            preparedStatement.setInt(1,user_id);
            ResultSet result = preparedStatement.executeQuery();
            result.next();
            res = result.getInt("count");
        }catch (Exception e){
            e.printStackTrace();
        }
        return res;
    }

    public static int numberOfUserGroups(int user_id){

        Connection connection = null;
        InitialContext initialContext = null;
        DataSource dataSource = null;
        int res = 0;

        try {
            initialContext = new InitialContext();
            dataSource = (DataSource) initialContext.lookup("java:/comp/env/jdbc/postgres");
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = null;
            preparedStatement = connection.prepareStatement(
                    "SELECT COUNT (*) FROM groups WHERE user_id = ?");
            preparedStatement.setInt(1,user_id);
            ResultSet result = preparedStatement.executeQuery();
            result.next();
            res = result.getInt("count");
        }catch (Exception e){
            e.printStackTrace();
        }

        return res;
    }
}
