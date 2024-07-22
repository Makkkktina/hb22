package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {
        public static void main(String[] args) {
            UserService userService = new UserServiceImpl();
            userService.createUsersTable();

            userService.saveUser("Иван", "Иванов", (byte) 30);
            userService.saveUser("Петр", "Петров", (byte) 25);
            userService.saveUser("Олег", "Олегов", (byte) 35);
            userService.saveUser("Ильяс", "Ильясов", (byte) 40);

            List<User> allUsers = userService.getAllUsers();
            for (User user : allUsers) {
                System.out.println(user.toString());
            }

            userService.cleanUsersTable();

            userService.dropUsersTable();
        }
}
