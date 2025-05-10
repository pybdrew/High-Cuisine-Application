package com.menu.model;

import jakarta.validation.constraints.*;

/**
 * A model class representing user login credentials.
 * 
 * Used to capture and validate username and password input during login.
 */
public class LoginModel
{
    /**
     * The username provided by the user.
     * Must be between 1 and 32 characters.
     */
    @Size(min = 1, max = 32, message = "User name is a required field")
    private String username;

    /**
     * The password provided by the user.
     * Must be between 8 and 32 characters and not null.
     */
    @NotNull(message = "Password is a required field")
    @Size(min = 8, max = 32, message = "Password must be at least 8 characters")
    private String password;

    /**
     * Gets the user's password.
     * @return the password
     */
    public String getPassword()
    {
        return password;
    }

    /**
     * Sets the user's password.
     * @param password the password to set
     */
    public void setPassword(String password)
    {
        this.password = password;
    }

    /**
     * Gets the user's username.
     * @return the username
     */
    public String getUsername()
    {
        return username;
    }

    /**
     * Sets the user's username.
     * @param username the username to set
     */
    public void setUsername(String username)
    {
        this.username = username;
    }
}