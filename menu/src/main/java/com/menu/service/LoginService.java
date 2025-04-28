package com.menu.service;

import com.menu.data.entity.UserEntity;
import com.menu.data.repository.UsersRepository;
import com.menu.model.RegisterModel;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginService
{
    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;

    public LoginService(UsersRepository usersRepository)
    {
        this.usersRepository = usersRepository;
        // Initialize the encoder
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public String validateLogin(String username, String password)
    {
        UserEntity user = usersRepository.findByUsername(username);
        if (user != null && passwordEncoder.matches(password, user.getPassword()))
        {
            // Adding ROLE_ prefix to match SecurityConfig
            return "ROLE_" + user.getRole();
        }
        return null;
    }

    public List<UserEntity> getAllUsers()
    {
        return (List<UserEntity>) usersRepository.findAll();
    }

    public void saveUser(RegisterModel registerModel, String role)
    {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(registerModel.getUsername());
        // Encode password before saving
        userEntity.setPassword(passwordEncoder.encode(registerModel.getPassword()));
        userEntity.setFirstName(registerModel.getFirstName());
        userEntity.setLastName(registerModel.getLastName());
        userEntity.setRole(role);

        usersRepository.save(userEntity);
    }

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
            // Encode password before updating
            user.setPassword(passwordEncoder.encode(model.getPassword()));
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
