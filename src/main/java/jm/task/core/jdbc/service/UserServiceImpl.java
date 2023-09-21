package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {
    private final UserDao UserDaoHibernateImpl = new UserDaoHibernateImpl();
    public void createUsersTable() {
        UserDaoHibernateImpl.createUsersTable();
    }

    public void dropUsersTable() {
        UserDaoHibernateImpl.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        UserDaoHibernateImpl.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) {
        UserDaoHibernateImpl.removeUserById(id);
    }

    public List<User> getAllUsers() {

        return UserDaoHibernateImpl.getAllUsers();
    }

    public void cleanUsersTable() {
        UserDaoHibernateImpl.cleanUsersTable();
    }
}
