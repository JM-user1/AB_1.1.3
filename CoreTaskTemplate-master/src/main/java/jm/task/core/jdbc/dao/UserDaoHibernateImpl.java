package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;
/*Класс UserHibernateDaoImpl в рамках этой задачи не затрагивается (остаётся пустой)*/
public class UserDaoHibernateImpl implements UserDao {

    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        try (Session session = Util.getSessionFactory().getCurrentSession()) {
            session.beginTransaction();
            session.createSQLQuery("create table if not exists user " +
                    "(id bigint not null primary key auto_increment, " +
                    "name varchar(25)," +
                    "lastName varchar(25), " +
                    "age int not null)").executeUpdate();
            session.getTransaction().commit();
            System.out.println("Таблица успешно создана.");
        } catch (Exception e) {
            System.out.println("Ошибка при создании таблицы -  " + e);
        }
    }

    @Override
    public void dropUsersTable() {//Удалить таблицу Юзер
        try (Session session = Util.getSessionFactory().getCurrentSession()) {
            session.beginTransaction();
            session.createSQLQuery("drop table if exists user").executeUpdate();
            session.getTransaction().commit();
            System.out.println("Таблица успешно удалена.");
        } catch (Exception e) {
            System.out.println("Ошибка при удалении таблицы -  " + e);
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = Util.getSessionFactory().getCurrentSession()){
            session.beginTransaction();
            session.save(new User(name, lastName, age));
            session.getTransaction().commit();
            System.out.println("Успешно добавлен пользователь с именем : " + name);
        } catch (Exception e) {
            System.out.println("Ошибка при сохранении пользователя -  " + e);
        }
    }

    @Override
    public void removeUserById(long id) {
        try  (Session session = Util.getSessionFactory().getCurrentSession()){
            session.beginTransaction();
            session.delete(id);
            session.getTransaction().commit();
            System.out.println("Успешно удален пользователь с id : " + id);
        } catch (Exception e) {
            System.out.println("Ошибка при удалении пользователя -  " + e);
        }
    }

    @Override
    public List<User> getAllUsers() {
        List res = null;
        try {
            Session session = Util.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            res = session.createQuery("from User").list();
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Ошибка при выводе пользователей -  " + e);
        }
        return res;
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = Util.getSessionFactory().getCurrentSession()) {
            session.beginTransaction();
            session.createSQLQuery("truncate table user").executeUpdate();
            session.getTransaction().commit();
            System.out.println("Таблица успешно очищена.");
        } catch (Exception e) {
            System.out.println("Ошибка при создании таблицы -  " + e);
        } 
    }
}
