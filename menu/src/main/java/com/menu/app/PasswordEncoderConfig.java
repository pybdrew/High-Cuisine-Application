package com.menu.app;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Configuration class for password encoding in the application.
 * 
 * Defines a Spring-managed bean that uses BCrypt to securely hash passwords.
 */
@Configuration
public class PasswordEncoderConfig
{

    /**
     * Provides a {@link PasswordEncoder} bean using the BCrypt hashing algorithm.
     *
     * @return a BCryptPasswordEncoder instance for secure password encoding
     */
    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }
}