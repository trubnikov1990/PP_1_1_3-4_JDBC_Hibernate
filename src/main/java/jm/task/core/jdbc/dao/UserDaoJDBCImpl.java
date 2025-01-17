package jm.task.core.jdbc.dao;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import jm.task.core.jdbc.model.User;

import java.util.List;
import jm.task.core.jdbc.util.Util;

public class UserDaoJDBCImpl extends Util implements UserDao {
    public UserDaoJDBCImpl() {
    }


    public void createUsersTable() {
        String sqlCommandCreateTable = "CREATE TABLE IF NOT EXISTS user (id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT, name VARCHAR(255) NOT NULL, lastName VARCHAR(255) NOT NULL, age TINYINT NOT NULL )";
        try {
            Statement statement = Util.getConnection().createStatement();
            statement.executeUpdate(sqlCommandCreateTable);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        String sqlCommandDropTable = "TRUNCATE TABLE  user";
        try {
            Statement  statement = Util.getConnection().createStatement();
            statement.executeUpdate(sqlCommandDropTable);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String sqlAddUser = "INSERT INTO user (name, lastName, age) VALUES (?, ?, ?)";
        try (PreparedStatement preStatement = Util.getConnection().prepareStatement(sqlAddUser)){

            preStatement.setString(1, name);
            preStatement.setString(2, lastName);
            preStatement.setByte(3, age);
            preStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        String sqlDeleteById = "DELETE FROM user WHERE ID = ?";
        try (PreparedStatement preparedStatement = Util.getConnection().prepareStatement(sqlDeleteById)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List <User> userList = new ArrayList<> ();
        String sqlReturnAllFields = "SELECT * FROM user";
        try (Statement statement = Util.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(sqlReturnAllFields)){

            while (resultSet.next()) {
                User userTable = new User();
                userTable.setId(resultSet.getLong("Id"));
                userTable.setName(resultSet.getString("name"));
                userTable.setLastName(resultSet.getString("lastName"));
                userTable.setAge(resultSet.getByte("age"));
                userList.add(userTable);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    public void cleanUsersTable() {
        String sqlCleanTable = "DELETE FROM user";
        try (Statement statement = Util.getConnection().createStatement()) {
            statement.executeUpdate(sqlCleanTable);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
