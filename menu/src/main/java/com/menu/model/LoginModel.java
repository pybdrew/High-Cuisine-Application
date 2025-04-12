package com.menu.model;

import jakarta.validation.constraints.*;

public class LoginModel
{
    @Size(min=1, max=32, message="User name is a required field")
    private String username;

    @NotNull(message="Password is a required field")
    @Size(min=8, max=32, message="Password must be atleast 8 characters")
    private String password;

    // Getters and Setters
    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }
}