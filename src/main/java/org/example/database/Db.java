package org.example.database;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.example.entity.User;

import java.io.*;
import java.lang.reflect.Type;
import java.util.*;

public class Db {
    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    private Db(){}

    ArrayList<User> users = null;

    public Optional<User> getUser(String email, String pass){
        for (User user : users) {
            if (user.getEmail().equals(email) && user.getPassword().equals(pass)){
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }

    public List<User> userList(){
        readUserFromFile();
        return users;
    }

    public void editUser(User user){
        if (users == null){
            readUserFromFile();
        }
        if (checkUser(user,true)){
            writeUserToFile();
        }
    }

    public void saveUser(User user){
        if (users == null){
            readUserFromFile();
        }
        if (checkUser(user,false)){
            users.add(user);
            System.out.println("Successfully added!");
            writeUserToFile();
            System.out.println("Successfully write to TXT file");
        } else {
            System.err.println("This email already exists!");
        }
    }

    private boolean checkUser(User user, boolean hasEdited) {
        for (User temp : users){
            if (hasEdited && temp.getEmail().equals(user.getEmail()) && !temp.getId().equals(user.getId())){
                return false;
            }
            if (!hasEdited && temp.getEmail().equals(user.getEmail())){
                return false;
            }
        }
        return true;
    }

    private void writeUserToFile() {
        String json = gson.toJson(users);
        try(BufferedWriter str = new BufferedWriter(new FileWriter("src\\main\\resources\\users.txt"))) {
            str.write(json);
        } catch (Exception e) {
            System.out.println("User not recorded!");
        }
    }

    private void readUserFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("src\\main\\resources\\users.json"))) {
            StringBuilder res = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                res.append(line);
            }
            Type token = new TypeToken<ArrayList<User>>() {
            }.getType();
            users = (ArrayList<User>) gson.fromJson(res.toString(), token);
        } catch (Exception e) {
            System.out.println("Cannot read from file!");
        }
    }

    private static Db db;
    public static Db getInstance(){
        if (db == null){
            db = new Db();
        }
        return db;
    }

}
