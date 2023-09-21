package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }

    private static final SessionFactory sessionFactory = Util.getHibernateConnection();
    private Transaction transaction;
    @Override
    public void createUsersTable() {
        try(Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();

            String sql = "CREATE TABLE User " +
                    "(id BIGINT AUTO_INCREMENT PRIMARY KEY, " +
                    "name VARCHAR(50), lastName VARCHAR(50), age TINYINT)";

            session.createSQLQuery(sql).executeUpdate();
            transaction.commit();
            System.out.println("Создана таблица");
        }
    }

    @Override
    public void dropUsersTable() {
        try(Session session = sessionFactory.getCurrentSession()) {
            transaction = session.beginTransaction();

            String sql = "DROP TABLE IF EXISTS User";

            session.createSQLQuery(sql).executeUpdate();
            transaction.commit();
        }
        System.out.println("Таблица удалена");

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {

        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            User user = new User(name,lastName,age);
            session.save(user);
            session.getTransaction().commit();
        }
        System.out.println("Добавлен человек " + name + " " + lastName + " " + age);
    }

    @Override
    public void removeUserById(long id) {
        try(Session session = sessionFactory.openSession()){
            session.delete(session.load(User.class,id));
            System.out.println("Удален человек с id = " + id);
        }
    }

    @Override
    public List<User> getAllUsers() {

        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from User ", User.class).getResultList();
        }

    }

    @Override
    public void cleanUsersTable() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.createSQLQuery("DELETE FROM User").executeUpdate();
            session.getTransaction().commit();

        }
    }
}
