package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Connection connection = Util.connect();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS Users (" +
                    "id INT PRIMARY KEY AUTO_INCREMENT," +
                    "name VARCHAR(20)," +
                    "lastName VARCHAR(20)," +
                    "age TINYINT)");
            System.out.println("Таблица Users создана.");
        } catch (SQLException e) {
            System.err.println("Ошибка при создании таблицы: " + e.getMessage());
        }
    }
    public void dropUsersTable() {
        try (Connection connection = Util.connect();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate("DROP TABLE IF EXISTS Users");
            System.out.println("Таблица Users удалена");
        } catch (SQLException e) {
            System.err.println("Ошибка при удалении таблицы: " + e.getMessage());
        }
    }


    public void saveUser(String name, String lastName, byte age) {
        try (Connection connection = Util.connect();
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Users (name, lastName, age) VALUES (?, ?, ?)")) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            System.out.println("Новый пользователь добавлен в таблицу.");
        } catch (SQLException e) {
            System.err.println("Ошибка при добавлении пользователя: " + e.getMessage());
        }
    }


    public void removeUserById(long id) {
        try (Connection connection = Util.connect();
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM Users WHERE id = ?")) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            System.out.println("Пользователь с ID " + id + " удален из таблицы.");
        } catch (SQLException e) {
            System.err.println("Ошибка при удалении пользователя: " + e.getMessage());
        }
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        try (Connection connection = Util.connect();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Users");
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }



    public void cleanUsersTable() {
        try (Connection connection = Util.connect();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate("TRUNCATE TABLE Users");
            System.out.println("Таблица Users очищена.");
        } catch (SQLException e) {
            System.err.println("Ошибка при очистке таблицы: " + e.getMessage());
        }

    }
}