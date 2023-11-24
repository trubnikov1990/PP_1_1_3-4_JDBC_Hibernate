package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {

        UserService service = new UserServiceImpl();

        service.createUsersTable();
        service.saveUser("Ivan", "Ivanov", (byte) 18);
        service.saveUser("Dmitry", "Trubnikov", (byte) 32);
        service.saveUser("Jenya", "MySon", (byte) 7);
        service.saveUser("Prosto", "Tak", (byte) 99);
        service.getAllUsers().forEach(System.out::println);
        service.cleanUsersTable();
        service.dropUsersTable();
    }
}
