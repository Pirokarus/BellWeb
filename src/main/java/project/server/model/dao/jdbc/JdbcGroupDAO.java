package project.server.model.dao.jdbc;

import project.server.factory.EntityFactory;
import project.server.model.dao.GroupDAO;
import project.server.model.data.Group;

import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.*;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JdbcGroupDAO implements GroupDAO {

    private String url = "jdbc:postgresql://localhost:5433/postgres";
    private String name = "postgres";
    private String password = "postgres";

    //@Override
    public void save(Group group, int user_id) throws Exception {
        Connection connection = null;
        InitialContext initialContext = null;
        DataSource dataSource = null;
        try {
            Class.forName("org.postgresql.Driver");
            initialContext = new InitialContext();
            dataSource = (DataSource) initialContext.lookup("java:/comp/env/jdbc/postgres");
            connection = dataSource.getConnection();

            PreparedStatement preparedStatement = null;

            preparedStatement = connection.prepareStatement(
                    "INSERT INTO groups( name, user_id) values(?,?)");
            preparedStatement.setString(1, group.getName());
            preparedStatement.setInt(2, user_id);

            preparedStatement.executeUpdate();


        } catch (Exception ex) {

            Logger.getLogger(JdbcContactDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(JdbcContactDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    //@Override
    public void removeById(int id) throws Exception {
        Connection connection = null;
        InitialContext initialContext = null;
        DataSource dataSource = null;
        try {
            Class.forName("org.postgresql.Driver");
            initialContext = new InitialContext();
            dataSource = (DataSource) initialContext.lookup("java:/comp/env/jdbc/postgres");
            connection = dataSource.getConnection();

            PreparedStatement preparedStatement = null;

            preparedStatement = connection.prepareStatement(
                    "DELETE FROM groups WHERE id = ?");
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();


        } catch (Exception ex) {

            Logger.getLogger(JdbcContactDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(JdbcContactDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    //@Override
    public void update(Group group, int id, int user_id) throws Exception {
        removeById(id);
        save(group, user_id);
    }

    //@Override
    public Set<Group> getAll(int user_id) throws Exception {
        Connection connection = null;
        Set<Group> groupSet = new HashSet<Group>();
        InitialContext initialContext = null;
        DataSource dataSource = null;
        try {
            Class.forName("org.postgresql.Driver");
            initialContext = new InitialContext();
            dataSource = (DataSource) initialContext.lookup("java:/comp/env/jdbc/postgres");
            connection = dataSource.getConnection();

            PreparedStatement preparedStatement = null;

            preparedStatement = connection.prepareStatement(
                    "SELECT * FROM groups WHERE user_id = ?");
            preparedStatement.setInt(1, user_id);

            ResultSet result = preparedStatement.executeQuery();

            while (result.next()) {

                groupSet.add((Group) EntityFactory.getEntity(result.getInt("id"),
                        result.getString("name")));
            }


        } catch (Exception ex) {

            Logger.getLogger(JdbcContactDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(JdbcContactDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return groupSet;
    }

    //@Override
    public Group getById(int id) throws Exception {
        Connection connection = null;
        Group group = null;
        InitialContext initialContext = null;
        DataSource dataSource = null;
        try {
            Class.forName("org.postgresql.Driver");
            initialContext = new InitialContext();
            dataSource = (DataSource) initialContext.lookup("java:/comp/env/jdbc/postgres");
            connection = dataSource.getConnection();

            PreparedStatement preparedStatement = null;

            preparedStatement = connection.prepareStatement(
                    "SELECT * FROM groups WHERE id = ?");
            preparedStatement.setInt(1,id);

            ResultSet result = preparedStatement.executeQuery();

            group = (Group) EntityFactory.getEntity(result.getInt("id"),
                    result.getString("name"));


        } catch (Exception ex) {

            Logger.getLogger(JdbcContactDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(JdbcContactDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return group;
    }
}
