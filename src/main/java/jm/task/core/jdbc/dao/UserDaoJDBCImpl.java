package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private static int id = 1;
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {

        String sql = "CREATE TABLE Users (id INT NOT NULL PRIMARY KEY AUTO_INCREMENT , first VARCHAR(255), last VARCHAR(255), age INTEGER)";
        try (Statement statement = Util.getConnection().createStatement()){
            statement.execute(sql);
            System.out.println("Таблица успешно создана");
        }
        catch (SQLException e){
            e.printStackTrace();
        }



    }

    public void dropUsersTable() {

        String sql = "DROP TABLE Users";
        try(Statement statement = Util.getConnection().createStatement()){
            statement.execute(sql);
            System.out.println("Таблица успешно удалена");
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {

        String sql = "INSERT INTO Users VALUES (?,?, ?, ?)";
        try {
            PreparedStatement preparedStatement = Util.getConnection().prepareStatement(sql);
            preparedStatement.setInt(1, id++);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, lastName);
            preparedStatement.setByte(4, age);
            preparedStatement.executeUpdate();
            System.out.println("Добавлен человек");
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        String sql = "DELETE FROM Users WHERE id=" + id;
        try(Statement statement = Util.getConnection().createStatement()){
            statement.execute(sql);
            System.out.println("Удален человек под id = " + id);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();


        String sql = "SELECT id, first, last, age FROM Users";
        try (Statement statement = Util.getConnection().createStatement()){
            statement.execute(sql);

            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("first"));
                user.setLastName(resultSet.getString("last"));
                user.setAge(resultSet.getByte("age"));
                list.add(user);
            }
            System.out.println(list);
            System.out.println("Выведены все пользователи");
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }

    public void cleanUsersTable() {
        String sql = "DELETE FROM Users";
        try(Statement statement = Util.getConnection().createStatement()){
            statement.execute(sql);
            System.out.println("Данные стерлись");
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
}
