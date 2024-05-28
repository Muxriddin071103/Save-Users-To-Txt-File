package org.example.service;

import org.example.database.Db;
import org.example.entity.User;
import org.example.util.Context;

import java.util.Optional;

import static org.example.util.Utils.*;

public class AuthService {
    private final Db db = Db.getInstance();
    private final UserService userService = UserService.getInstance();

    public void auth(){
        while (true){
            System.out.println("""
                    0 exit
                    1 sign in
                    2 sign up
                    3 show users
                    """);
            switch (scanner.nextInt()){
                case 0 -> {
                    System.out.println("See you!");
                    return;
                }
                case 1 -> {
                    System.out.print("Enter e-mail: ");
                    String email = strScanner.nextLine();
                    System.out.print("Enter password: ");
                    String pass = strScanner.nextLine();
                    Optional<User> optionalUser = db.getUserByEmailAndPassword(email,pass);
                    if (optionalUser.isPresent()){
                        Context.setUser(optionalUser.get());
                        userService.service();
                    } else {
                        System.err.println("User not found!");
                    }
                }
                case 2 -> {
                    System.out.print("Enter e-mail: ");
                    String email = strScanner.nextLine();
                    System.out.print("Enter password: ");
                    String password = strScanner.nextLine();
                    System.out.print("Enter name: ");
                    String name = strScanner.nextLine();
                    System.out.print("Enter lastname: ");
                    String lastname = strScanner.nextLine();
                    System.out.print("Enter age: ");
                    Integer age = scanner.nextInt();

                    User user = new User(name,lastname,email,password,age);
                    db.saveUser(user);
                }
                case 3 -> {
                    System.out.println("The already sign in users: ");
                    for (User user : db.userList()){
                        System.out.println(user);
                    }
                }
            }
        }
    }

    private static AuthService authService;
    public static AuthService getInstance(){
        if (authService == null){
            authService = new AuthService();
        }
        return authService;
    }
}
