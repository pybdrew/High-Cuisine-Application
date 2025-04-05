// src/main/java/com/menu/service/LoginService.java
package com.menu.service;

import org.springframework.stereotype.Service;

@Service
public class LoginService 
{
    public String validateLogin(String username, String password) 
    {
        // Dummy check - replace with real login logic later
        if ("admin".equals(username) && "password".equals(password)) 
        {
            // Return role as 'admin' if login is successful
            return "admin";  
        }
        return null;
    }
}