package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

    public class Util {
        private static final String URL = "jdbc:mysql://localhost:3306/mysql";
        private static final String USER = "root";
        private static final String PASSWORD = "root";

        public static Connection connect() {
            Connection connection = null;
            try {
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("Соединение с БД успешно установлено!");
            } catch (SQLException e) {
                System.err.println("Ошибка при подключении к БД: " + e.getMessage());
            }
            return connection;
        }

        public static void main(String[] args) {

            connect();
        }
    }


