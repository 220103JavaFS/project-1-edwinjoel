package com.revature.repository;

import com.revature.model.User;
import com.revature.model.UserRole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.revature.JDBCPostgreSQLConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDAOImpl implements UserDAO {

    private static Logger log = LoggerFactory.getLogger(com.revature.App.class);

    @Override
    public boolean addUser(User user) {
        try (Connection connection = JDBCPostgreSQLConnection.getConnection()) {
            String sql = "INSERT INTO ers_users (ers_username, ers_password, user_first_name, user_last_name, user_email, user_role_id) VALUES (?,?,?,?,?,?);";
            PreparedStatement statement = connection.prepareStatement(sql);

            int count = 1;
            statement.setString(count++, user.getUsername());
            statement.setBytes(count++, user.getHash());
            statement.setString(count++, user.getFirstName());
            statement.setString(count++, user.getLastName());
            statement.setString(count++, user.getEmail());
            statement.setInt(count++, user.getRoleId());

            statement.execute();
            log.debug("Added User to Database");
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        log.error("Failed to Add User to Database");
        return false;
    }

    @Override
    public ArrayList<User> getAllUsers() {
        try (Connection connection = JDBCPostgreSQLConnection.getConnection()) {
            ArrayList<User> users = new ArrayList<>();
            ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM ers_users INNER JOIN ers_user_roles ON user_role_id = ers_user_role_id;");
            while (resultSet.next()) {
                users.add(createUserObj(resultSet));
            }
            log.debug("Got UserList from database");
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        log.error("Failed to Get UserList from Database");
        return new ArrayList<User>();

    }

    @Override
    public User getUser(String username) {
        try (Connection connection = JDBCPostgreSQLConnection.getConnection()) {
            String sql = "SELECT * FROM ers_users INNER JOIN ers_user_roles ON user_role_id = ers_user_role_id WHERE ers_username = ?;";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);

            ResultSet resultSet = statement.executeQuery();
            User user = null;
            if (resultSet.next()) {
                user = createUserObj(resultSet);
            }
            log.debug("Got User from database with username");
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        log.error("Failed to Get User from Database with username");
        return null;
    }

    @Override
    public User getUserById(int userId) {
        try (Connection connection = JDBCPostgreSQLConnection.getConnection()) {
            String sql = "SELECT * FROM ers_users INNER JOIN ers_user_roles ON user_role_id = ers_user_role_id WHERE ers_user_id = ?;";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, userId);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                log.debug("Got User from database with user_id");
                return createUserObj(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        log.error("Failed to Get User from Database with user_id");
        return null;
    }

    @Override
    public boolean updateUser(User user, int userId) {
        try (Connection connection = JDBCPostgreSQLConnection.getConnection()) {
            String sql = "UPDATE ers_users SET ers_username = ?, user_first_name = ?, user_last_name = ?, user_email = ?, user_role_id = ? WHERE ers_user_id = ?;";
            PreparedStatement statement = connection.prepareStatement(sql);

            User oldUser = this.getUserById(userId);
            if(oldUser == null){
                return false;
            }



            int count = 1;
            statement.setString(count++, user.getUsername() == null ? oldUser.getUsername() : user.getUsername());
            statement.setString(count++, user.getFirstName() == null ? oldUser.getFirstName() : user.getFirstName());
            statement.setString(count++, user.getLastName() == null ? oldUser.getLastName() : user.getLastName());
            statement.setString(count++, user.getEmail() == null ? oldUser.getEmail() : user.getEmail());
            statement.setInt(count++, user.getRoleId() == 0 ? oldUser.getRoleId() : user.getRoleId());
            statement.setInt(count++, userId);

            statement.execute();
            log.debug("Updated User in the Database");
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        log.error("Failed to Updated User in the Database");
        return false;
    }

    @Override
    public boolean deleteUser(String username) {
        try(Connection connection = JDBCPostgreSQLConnection.getConnection()){
            String sql = "DELETE FROM ers_users WHERE ers_username = ? ;";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            statement.execute();

            return true;

        }
        catch (SQLException e){
            e.printStackTrace();
        }
        log.error("Failed to Delete User from Database with username: " + username);
        return false;
    }





    //helper method to create a user object from the ResultSet object representing the SQL query
    private static User createUserObj(ResultSet resultSet) throws SQLException {
        int userId = resultSet.getInt("ers_user_id");
        String username = resultSet.getString("ers_username");
        String firstName = resultSet.getString("user_first_name");
        String lastName = resultSet.getString("user_last_name");
        String email = resultSet.getString("user_email");
        byte[] hash = resultSet.getBytes("ers_password");
        int roleId = resultSet.getInt("user_role_id");
        UserRole userRole = UserRole.EMPLOYEE;

        try{
            userRole = UserRole.valueOf(resultSet.getString("user_role"));
        }
        catch (IllegalArgumentException e){
            if(userId == 2) {
                userRole = UserRole.MANAGER;
            }
        }


        return new User(userId, username, hash, firstName, lastName, email, roleId, userRole);
    }
}
