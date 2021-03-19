package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.SQLException;

public class Main {
/*
 Создание таблицы User(ов)
 Добавление 4 User(ов) в таблицу с данными на свой выбор. После каждого добавления должен быть вывод в консоль ( User с именем – name добавлен в базу данных )
 Получение всех User из базы и вывод в консоль ( должен быть переопределен toString в классе User)
 Очистка таблицы User(ов)
 Удаление таблицы
 */
    public static void main(String[] args) throws SQLException {// реализуйте алгоритм здесь
        UserService userService = new UserServiceImpl();

        userService.createUsersTable();
        userService.saveUser("Ви", "Просто Ви", (byte) 20);
        userService.saveUser("Джонни", "Сильверхенд", (byte) 27);
        userService.saveUser("Джуди", "Альварес", (byte) 20);
        userService.saveUser("Керри", "Евродин", (byte) 28);

        for (User user : userService.getAllUsers()){
            System.out.println(user.toString());
        }
        userService.cleanUsersTable();
        userService.dropUsersTable();
        Util.closeSessionFactory();
        System.out.println("Утка");
    }
}
