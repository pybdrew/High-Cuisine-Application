package com.menu.service;

import com.menu.data.entity.UserEntity;
import com.menu.data.repository.UsersRepository;
import com.menu.model.RegisterModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterService
{
    private final UsersRepository usersRepository;

    @Autowired
    public RegisterService(UsersRepository usersRepository)
    {
        this.usersRepository = usersRepository;
    }

    public boolean registerUser(RegisterModel model)
    {
        // Check if username already exists
        if (usersRepository.findByUsername(model.getUsername()) != null)
        {
            return false;
        }

        // Convert model to entity
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(model.getUsername());
        userEntity.setPassword(model.getPassword()); 
        userEntity.setFirstName(model.getFirstName());
        userEntity.setLastName(model.getLastName());

        // Make first user registration admin
        if (usersRepository.count() == 0)
        {
            userEntity.setRole("admin"); 
        }
        else
        {
            // Everyone else regular (for now)
            userEntity.setRole("user"); 
        }

        usersRepository.save(userEntity);
        return true;
    }
}
