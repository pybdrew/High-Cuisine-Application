package com.menu.service;

import org.springframework.stereotype.Service;
import com.menu.model.RegisterModel;

@Service
public class RegisterService
{

    public boolean registerUser(RegisterModel user)
    {
        // Simulate saving user — in a real app this would go to a DB
        System.out.println("Registering user: " + user.getUsername());
        // Always succeeds for now
        return true;
    }
}