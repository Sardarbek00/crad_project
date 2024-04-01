package org.example;

import org.example.model.User;
import org.example.service.UserService;

import java.sql.SQLException;
import java.util.List;

public class App {
    public static void main(String[] args) {
        UserService userService = new UserService();

        userService.createTable();
      //  userService.saveUser(new User("Udino", "Juien"));
       //userService.saveUser(new User("Salam", "vsem"));

      //  User byId = userService.findById(3);
      //  System.out.println(byId);

       // List<User>users=userService.finAll();
      //  users.forEach(System.out::println);
       // userService.deleteById(4);
       // System.out.println(userService.finAll());

       // for (int i=1;i<=9;i++){
           // userService.deleteById(i);
       // }
userService.updateById(11,new User("All","My life"));
        //  try {
         //   userService.dropTable();
       // } catch (SQLException e) {
        //    System.out.println(e.getMessage());
        //}
    }
}