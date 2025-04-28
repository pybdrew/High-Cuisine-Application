package com.menu.service;

import com.menu.data.access.UsersDataAccessInterface;
import com.menu.data.entity.UserEntity;
import com.menu.data.repository.UsersRepository;
import org.springframework.stereotype.Service;

@Service
public class UsersDataService implements UsersDataAccessInterface<UserEntity>
{

    private final UsersRepository usersRepository;

    public UsersDataService(UsersRepository usersRepository)
    {
        this.usersRepository = usersRepository;
    }

    @Override
    public UserEntity findByUsername(String username)
    {
        return usersRepository.findByUsername(username);
    }
}
