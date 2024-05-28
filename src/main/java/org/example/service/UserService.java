package org.example.service;

import org.example.database.Db;
import org.example.entity.User;
import org.example.util.Context;

import static org.example.util.Utils.*;

public class UserService {
    private final Db db = Db.getInstance();

    public void service(){
        while (true){
            System.out.println("""
                    0 exit
                    1 show info
                    2 edit info
                    """);
            switch (scanner.nextInt()){
                case 0 -> {
                    System.out.println("See you soon " + Context.getCurUser().getName());
                    return;
                }
                case 1 -> {
                    User user = Context.getCurUser();
                    System.out.println("====================================================================================");
                    System.out.println("Name = " + user.getName());
                    System.out.println("Lastname = " + user.getLastname());
                    System.out.println("E-mail = " + user.getEmail());
                    System.out.println("Password = " + user.getPassword());
                    System.out.println("Age = " + user.getAge());
                    System.out.println("====================================================================================");
                }
                case 2 -> {
                    User user = Context.getCurUser();
                    System.out.println("""
                            0 EXIT
                            1 NAME
                            2 LASTNAME
                            3 E-MAIL
                            4 PASSWORD
                            5 AGE
                            """);
                    switch (scanner.nextInt()){
                        case 0 -> {
                            break;
                        }
                        case 1 -> {
                            System.out.println("The current name = " + user.getName());
                            System.out.print("Enter new name = ");
                            user.setName(strScanner.nextLine());
                        }
                        case 2-> {
                            System.out.println("The current lastname = " + user.getLastname());
                            System.out.print("Enter new lastname = ");
                            user.setLastname(strScanner.nextLine());
                        }
                        case 3 -> {
                            System.out.println("The current e-mail = " + user.getEmail());
                            System.out.print("Enter new e-mail = ");
                            user.setEmail(strScanner.nextLine());
                        }
                        case 4 -> {
                            System.out.println("The current password = " + user.getPassword());
                            System.out.print("Enter new password = ");
                            user.setPassword(strScanner.nextLine());
                        }
                        case 5 -> {
                            System.out.println("The current age = " + user.getAge());
                            System.out.print("Enter new age = ");
                            user.setAge(scanner.nextInt());
                        }
                    }
                    db.editUser(user);
                }
            }
        }
    }

    private static UserService userService;
    public static UserService getInstance(){
        if (userService == null) {
            userService = new UserService();
        }
        return userService;
    }
}
