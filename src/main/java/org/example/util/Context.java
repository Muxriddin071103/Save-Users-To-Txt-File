package org.example.util;

import org.example.entity.User;

public class Context {
    private static User currentUser;

    public static void setUser(User user){
        currentUser = user;
    }

    public static User getCurUser(){
        return currentUser;
    }
}
