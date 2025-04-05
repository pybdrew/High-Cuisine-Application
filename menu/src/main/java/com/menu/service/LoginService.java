// src/main/java/com/menu/service/LoginService.java
package com.menu.service;

import org.springframework.stereotype.Service;

@Service
public class LoginService {
    public boolean validateLogin(String username, String password) {
        // Dummy check - replace with real login logic later
        return "admin".equals(username) && "password".equals(password);
    }
}