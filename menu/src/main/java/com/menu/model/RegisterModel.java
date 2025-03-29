package com.menu.model;

import jakarta.validation.constraints.*;

public class RegisterModel
{
    @NotNull(message="User name is a required field")
    @Size(min=1, max=32, message="•User name must be between 1 and 32 characters")
    private String username;

    @NotNull(message="Password is a required field")
    @Size(min=1, max=32, message="•Password must be atleast 8 characters and no more than 32 characters")
    @Pattern
    (
        regexp = "^(?=.*[A-Z])(?=.*[!@#$*])[A-Za-z\\d!@#$*]{8,32}$",
        message = "Password must contain at least one uppercase letter and one special character (!, @, #, $, *)"
    )
    private String password;

    @NotNull(message="First name is a required field")
    @Size(min=1, max=32, message="•First name must be between 1 and 32 characters")
    private String firstName;

    @NotNull(message="Last name is a required field")
    @Size(min=1, max=32, message="•Last name must be between 1 and 32 characters")
    private String lastName;


    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

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
