package com.menu.service;

import com.menu.data.entity.UserEntity;
import com.menu.data.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService 
{
    private final UsersRepository usersRepository;

    @Autowired
    public LoginService(UsersRepository usersRepository) 
    {
        this.usersRepository = usersRepository;
    }

    public String validateLogin(String username, String password) 
    {
        // Attempt to find the user by username
        UserEntity user = usersRepository.findByUsername(username);

        // Check if the user exists and the password matches
        if (user != null && user.getPassword().equals(password)) 
        {
            // Return the role of the user (assuming it's stored in the UserEntity)
            return user.getRole();  
        }
        return null;
    }
}
