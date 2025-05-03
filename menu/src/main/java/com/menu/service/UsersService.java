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

@Service
public class UsersService implements org.springframework.security.core.userdetails.UserDetailsService
{

    private final UsersRepository usersRepository;
    // Constructor for dependency injection
    public UsersService(UsersRepository usersRepository, PasswordEncoder passwordEncoder)
    {
        this.usersRepository = usersRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        // Retrieve user from the repository based on the username
        UserEntity userEntity = usersRepository.findByUsername(username);

        // If the user is not found, throw an exception
        if (userEntity == null)
        {
            throw new UsernameNotFoundException("User not found");
        }

        // Create a set of authorities (roles) based on user’s role(s) from the database
        Set<GrantedAuthority> authorities = new HashSet<>();
        String role = userEntity.getRole().toLowerCase();  // Assuming role is stored in lowercase in the database

        // Assign roles based on the role from the database
        if ("admin".equals(role))
        {
            // Role 'admin'
            authorities.add(new SimpleGrantedAuthority("ROLE_admin"));
        }
        else if ("staff".equals(role))
        {
            // Role 'staff'
            authorities.add(new SimpleGrantedAuthority("ROLE_staff"));
        }
        else
        {
             // Default role 'user'
            authorities.add(new SimpleGrantedAuthority("ROLE_user"));
        }

        // Return the user with their roles and encrypted password
        return new User(
                userEntity.getUsername(),
                // The encrypted password from the database
                userEntity.getPassword(),
                // User's roles (authorities)
                authorities
        );
    }
}
