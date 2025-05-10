package com.menu.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.menu.data.entity.UserEntity;
import com.menu.data.repository.UsersRepository;

import java.util.*;

/**
 * Service class that implements Spring Security's {@link org.springframework.security.core.userdetails.UserDetailsService}.
 * 
 * Responsible for loading user details during authentication based on the username.
 */
@Service
public class UsersService implements org.springframework.security.core.userdetails.UserDetailsService
{
    private final UsersRepository usersRepository;

    /**
     * Constructs the {@code UsersService} with injected dependencies.
     *
     * @param usersRepository the repository for accessing user data
     * @param passwordEncoder the password encoder (not used directly but required for context)
     */
    public UsersService(UsersRepository usersRepository, PasswordEncoder passwordEncoder)
    {
        this.usersRepository = usersRepository;
    }

    /**
     * Loads the user details by username for Spring Security authentication.
     *
     * @param username the username to look up
     * @return a Spring Security {@link UserDetails} object with roles and credentials
     * @throws UsernameNotFoundException if no user is found with the given username
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        UserEntity userEntity = usersRepository.findByUsername(username);

        if (userEntity == null)
        {
            throw new UsernameNotFoundException("User not found");
        }

        Set<GrantedAuthority> authorities = new HashSet<>();
        String role = userEntity.getRole().toLowerCase();

        switch (role)
        {
            case "admin":
                authorities.add(new SimpleGrantedAuthority("ROLE_admin"));
                break;
            case "staff":
                authorities.add(new SimpleGrantedAuthority("ROLE_staff"));
                break;
            default:
                authorities.add(new SimpleGrantedAuthority("ROLE_user"));
                break;
        }

        return new User(
            userEntity.getUsername(),
            userEntity.getPassword(),
            authorities
        );
    }
}