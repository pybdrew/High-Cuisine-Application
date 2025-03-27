package com.menu.model;

import jakarta.validation.constraints.*;

public class LoginModel
{
    @NotNull(message="User name is a required field")
    @Size(min=1, max=32, message="User name must be between 8 and 20 characters")
    private String username;

    @NotNull(message="Password is a required field")
    @Size(min=1, max=2, message="Password must be between 8 and 20 characters")
    private String password;

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