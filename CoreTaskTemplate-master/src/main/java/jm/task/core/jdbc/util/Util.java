package jm.task.core.jdbc.util;

import java.sql.*;

public class Util {
    // реализуйте настройку соеденения с БД
    public static Connection getConnection() throws SQLException {
        String password = "root";
        String login = "newroot";
        String url = "jdbc:mysql://localhost:3306/it_aleksa";
        return DriverManager.getConnection(url, login, password);
    }

}
