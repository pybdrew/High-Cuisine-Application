package com.menu.service;

import com.menu.data.access.UsersDataAccessInterface;
import com.menu.data.entity.UserEntity;
import com.menu.data.repository.UsersRepository;
import org.springframework.stereotype.Service;

/**
 * Service class that implements {@link UsersDataAccessInterface}
 * to provide access to user data using the {@link UsersRepository}.
 */
@Service
public class UsersDataService implements UsersDataAccessInterface<UserEntity>
{
    private final UsersRepository usersRepository;

    /**
     * Constructs a {@code UsersDataService} with the provided user repository.
     *
     * @param usersRepository the repository used to access user data
     */
    public UsersDataService(UsersRepository usersRepository)
    {
        this.usersRepository = usersRepository;
    }

    /**
     * Finds a user by their username.
     *
     * @param username the username to search for
     * @return the matching {@link UserEntity}, or {@code null} if not found
     */
    @Override
    public UserEntity findByUsername(String username)
    {
        return usersRepository.findByUsername(username);
    }
}