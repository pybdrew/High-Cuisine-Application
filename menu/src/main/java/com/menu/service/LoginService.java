package com.menu.service;

import com.menu.data.entity.UserEntity;
import com.menu.data.repository.UsersRepository;
import com.menu.model.RegisterModel;

import java.util.*;

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

    public List<UserEntity> getAllUsers()
    {
        return (List<UserEntity>) usersRepository.findAll();
    }

    // Method to save a new user with a specified role (for admin use)
    public void saveUser(RegisterModel registerModel, String role)
    {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(registerModel.getUsername());
        // Have not added encoding
        userEntity.setPassword(registerModel.getPassword());
        userEntity.setFirstName(registerModel.getFirstName());
        userEntity.setLastName(registerModel.getLastName());
        // role passed from controller
        userEntity.setRole(role);

        usersRepository.save(userEntity);
    }

    // Following methods are user edit/delete functionality for admin
    public UserEntity getUserById(Long id)
    {
        return usersRepository.findById(id).orElse(null);
    }
    
    public void updateUser(Long id, RegisterModel model, String role)
    {
        UserEntity user = getUserById(id);
        if (user != null)
        {
            user.setUsername(model.getUsername());
            // no encoding added yet
            user.setPassword(model.getPassword());
            user.setFirstName(model.getFirstName());
            user.setLastName(model.getLastName());
            user.setRole(role);
            usersRepository.save(user);
        }
    }
    
    public void deleteUser(Long id)
    {
        usersRepository.deleteById(id);
    }
    
    
}
