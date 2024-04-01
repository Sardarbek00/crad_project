package org.example.service;

import org.example.config.DataBaseConfig;
import org.example.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserService {
    public void createTable() {
        String query = """
                CREATE TABLE IF NOT EXISTS users(
                    id SERIAL PRIMARY KEY ,
                    name VARCHAR NOT NULL ,
                    last_name VARCHAR
                );
                      """;

        try (Connection connection = DataBaseConfig.getConnection()) {
            Statement statement = connection.createStatement();
            statement.execute(query);
            System.out.println("users table successfully created ");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUser(User user) {
        String query = """
                INSERT INTO users(name, last_name)
                VALUES (?,?);
                          """;
        try (Connection connection = DataBaseConfig.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.execute();
            System.out.println("Your user saved");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void dropTable() throws SQLException {
        Connection connection = DataBaseConfig.getConnection();
        Statement statement = connection.createStatement();
        statement.execute("DROP TABLE users");
        connection.close();
        System.out.println("Your table is dropped");
    }

    public User findById(int id) {
        String query = "SELECT * FROM users id=?";
        User user = new User();
        try (Connection connection = DataBaseConfig.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("last_name"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return user;
    }

    public List<User> finAll() {
        String query = " SELECT * FROM users";
        List<User> userList = new ArrayList<>();
        try (Connection connection = DataBaseConfig.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("last_name"));
                userList.add(user);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return userList;
    }

    public void deleteById(int id) {
        String query = "DELETE FROM users WHERE id=?";
        try (Connection connection = DataBaseConfig.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            System.out.println("user with id :" + id + "deleted");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void updateById(int id,User newUser){
        String query= """
                      UPDATE users SET name=?,last_name=? WHERE id=?;
                      """;
        try(Connection connection=DataBaseConfig.getConnection()){
            PreparedStatement preparedStatement=connection.prepareStatement(query);
            preparedStatement.setString(1,newUser.getName());
            preparedStatement.setString(2,newUser.getLastName());
            preparedStatement.setInt(3,id);
            preparedStatement.execute();
            System.out.println("user with id:"+id+ "successfully updated");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }
}
