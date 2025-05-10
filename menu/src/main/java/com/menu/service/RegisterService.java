package com.menu.service;

import com.menu.data.entity.UserEntity;
import com.menu.data.repository.UsersRepository;
import com.menu.model.RegisterModel;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Service class responsible for handling user registration logic.
 * 
 * Validates unique usernames and assigns roles based on user count.
 */
@Service
public class RegisterService {

    private final UsersRepository usersRepository;

    /**
     * Constructs a {@code RegisterService} with required dependencies.
     *
     * @param usersRepository the repository for accessing user data
     * @param passwordEncoder the encoder used for hashing passwords (not stored directly but passed during registration)
     */
    public RegisterService(UsersRepository usersRepository, PasswordEncoder passwordEncoder)
    {
        this.usersRepository = usersRepository;
    }

    /**
     * Registers a new user based on the given registration model and encoded password.
     *
     * @param model the registration form data
     * @param encodedPassword the already-hashed password to save
     * @return {@code true} if registration was successful, {@code false} if username already exists
     */
    public boolean registerUser(RegisterModel model, String encodedPassword)
    {
        // Reject duplicate usernames
        if (usersRepository.findByUsername(model.getUsername()) != null)
        {
            return false;
        }

        // Convert model to entity and populate fields
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(model.getUsername());
        userEntity.setPassword(encodedPassword);
        userEntity.setFirstName(model.getFirstName());
        userEntity.setLastName(model.getLastName());

        // First registered user becomes admin
        if (usersRepository.count() == 0)
        {
            userEntity.setRole("admin");
        }
        else
        {
            userEntity.setRole("user");
        }

        usersRepository.save(userEntity);
        return true;
    }
}