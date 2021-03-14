package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {//Создать таблицу юзеров
        try (Connection conn = Util.getConnection();
             Statement stat = conn.createStatement()){
            stat.executeUpdate("create table if not exists user (id bigint not null primary key auto_increment, name varchar(25),lastName varchar(25), age int not null)");
            System.out.println("Таблица успешно создана.");
        } catch ( Exception e) {
            System.err.println("Ошибка при создании таблицы!" + e);
        }
    }

    public void dropUsersTable() {//Удалить таблицу Юзер
        try (Connection conn = Util.getConnection();
             Statement stat = conn.createStatement()){
            stat.executeUpdate("drop table if exists user");
            System.out.println("Таблица успешно удалена.");
        } catch (SQLException e) {
            System.err.println("Ошибка при удалении таблицы!"+ e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {//Добавление юзера в таблицу
        try (Connection conn = Util.getConnection();
             Statement stat = conn.createStatement()){
            stat.executeUpdate("insert into user(name, lastName, age) VALUES ('" + name + "', '" + lastName + "', " + age + ")");
            System.out.println("User с именем – " + name + " добавлен(а) в базу данных.");
        } catch (SQLException e) {
            System.err.println("Ошибка при добавлении юзера!");
        }
    }

    public void removeUserById(long id) {//Удаление юзера из таблицы
        try (Connection conn = Util.getConnection();
             Statement stat = conn.createStatement()){
            stat.executeUpdate("delete from user where id=" + id);
            System.out.println("User успешно удален из таблицы.");
        } catch (SQLException e) {
            System.err.println("Ошибка при удалении юзера!");
        }
    }

    public List<User> getAllUsers() {//вывод всех юзеров
        List<User> res = new ArrayList<>();
        String query = "select * from user";
        try (Connection conn = Util.getConnection();
             Statement stat = conn.createStatement()){
            ResultSet resultSet = stat.executeQuery(query);
            while (resultSet.next()) {
                res.add(new User(resultSet.getLong(1), resultSet.getString(2), resultSet.getString(3), resultSet.getByte(4)));
            }
        } catch (Exception e) {
            System.err.println("Ошибка при выводе юзеров!");
        }
        return res;
    }

    public void cleanUsersTable() {//очистка тадлицы
        try (Connection conn = Util.getConnection();
             Statement stat = conn.createStatement()){
            stat.executeUpdate("truncate table user");
            System.out.println("Таблица успешно очищена.");
        } catch (SQLException e) {
            System.err.println("Ошибка при отчистке таблицы");
        }
    }
}
