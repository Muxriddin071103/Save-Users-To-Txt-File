package org.example;

import lombok.extern.java.Log;
import org.example.service.AuthService;

@Log
public class Main {
    public static void main(String[] args) {
    new AuthService().auth();
    }
}