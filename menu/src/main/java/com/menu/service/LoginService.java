package com.menu.service;

import com.menu.data.entity.UserEntity;
import com.menu.data.repository.UsersRepository;
import com.menu.model.RegisterModel;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for managing user authentication and administration.
 * 
 * Handles login validation, user creation, updating, retrieval, and deletion.
 */
@Service
public class LoginService
{
    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * Constructs the {@code LoginService} with repository injection.
     *
     * Initializes the password encoder using BCrypt.
     *
     * @param usersRepository the repository used to access user data
     */
    public LoginService(UsersRepository usersRepository)
    {
        this.usersRepository = usersRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    /**
     * Validates login credentials by checking username and encoded password.
     *
     * @param username the username entered by the user
     * @param password the plain-text password entered by the user
     * @return the user's role prefixed with "ROLE_", or null if invalid
     */
    public String validateLogin(String username, String password)
    {
        UserEntity user = usersRepository.findByUsername(username);
        if (user != null && passwordEncoder.matches(password, user.getPassword()))
        {
            return "ROLE_" + user.getRole();
        }
        return null;
    }

    /**
     * Retrieves all users from the database.
     *
     * @return a list of all {@link UserEntity} objects
     */
    public List<UserEntity> getAllUsers()
    {
        return (List<UserEntity>) usersRepository.findAll();
    }

    /**
     * Saves a new user to the database using information from the registration model.
     *
     * @param registerModel the model containing user information
     * @param role the role to assign to the user
     */
    public void saveUser(RegisterModel registerModel, String role)
    {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(registerModel.getUsername());
        userEntity.setPassword(passwordEncoder.encode(registerModel.getPassword()));
        userEntity.setFirstName(registerModel.getFirstName());
        userEntity.setLastName(registerModel.getLastName());
        userEntity.setRole(role);

        usersRepository.save(userEntity);
    }

    /**
     * Retrieves a user by their ID.
     *
     * @param id the user ID
     * @return the {@link UserEntity} or null if not found
     */
    public UserEntity getUserById(Long id)
    {
        return usersRepository.findById(id).orElse(null);
    }

    /**
     * Updates an existing user's information.
     *
     * @param id the user ID
     * @param model the updated registration model
     * @param role the updated role
     */
    public void updateUser(Long id, RegisterModel model, String role)
    {
        UserEntity user = getUserById(id);
        if (user != null)
        {
            user.setUsername(model.getUsername());
            user.setPassword(passwordEncoder.encode(model.getPassword()));
            user.setFirstName(model.getFirstName());
            user.setLastName(model.getLastName());
            user.setRole(role);
            usersRepository.save(user);
        }
    }

    /**
     * Deletes a user from the database by ID.
     *
     * @param id the ID of the user to delete
     */
    public void deleteUser(Long id)
    {
        usersRepository.deleteById(id);
    }
}