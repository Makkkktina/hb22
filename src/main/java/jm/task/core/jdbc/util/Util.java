package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String URL = "jdbc:mysql://localhost:3306/mysql";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    private static SessionFactory sessionFactory;

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

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration()
                        .setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect")
                        .setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver")
                        .setProperty("hibernate.connection.url", URL)
                        .setProperty("hibernate.connection.username", USER)
                        .setProperty("hibernate.connection.password", PASSWORD)
                        .setProperty("hibernate.show_sql", "true")
                        .setProperty("hbm2ddl.auto", "create-drop") //
                        .addAnnotatedClass(User.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                System.err.println("Ошибка при создании SessionFactory: " + e.getMessage());
            }
        }
        return sessionFactory;
    }

    public static void main(String[] args) {
        connect();
        getSessionFactory();
    }
}
