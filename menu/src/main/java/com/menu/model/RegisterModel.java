package com.menu.model;

import jakarta.validation.constraints.*;

/**
 * A model class used for registering new users.
 * 
 * Contains validation rules for username, password, and personal details.
 * Used in form submissions during the registration process.
 */
public class RegisterModel
{
    /**
     * Username for the account.
     * Must be between 1 and 32 characters.
     */
    @NotNull(message = "User name is a required field")
    @Size(min = 1, max = 32, message = "•User name must be between 1 and 32 characters")
    private String username;

    /**
     * Password for the account.
     * Must be between 8 and 32 characters, contain at least one uppercase letter,
     * and include one special character (!, @, #, $, *).
     */
    @NotNull(message = "Password is a required field")
    @Size(min = 1, max = 32, message = "•Password must be at least 8 characters and no more than 32 characters")
    @Pattern(
        regexp = "^(?=.*[A-Z])(?=.*[!@#$*])[A-Za-z\\d!@#$*]{8,32}$",
        message = "Password must contain at least one uppercase letter and one special character (!, @, #, $, *)"
    )
    private String password;

    /**
     * First name of the user.
     * Must be between 1 and 32 characters.
     */
    @NotNull(message = "First name is a required field")
    @Size(min = 1, max = 32, message = "•First name must be between 1 and 32 characters")
    private String firstName;

    /**
     * Last name of the user.
     * Must be between 1 and 32 characters.
     */
    @NotNull(message = "Last name is a required field")
    @Size(min = 1, max = 32, message = "•Last name must be between 1 and 32 characters")
    private String lastName;

    /**
     * Gets the user's last name.
     * @return the last name
     */
    public String getLastName()
    {
        return lastName;
    }

    /**
     * Sets the user's last name.
     * @param lastName the last name to set
     */
    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    /**
     * Gets the user's first name.
     * @return the first name
     */
    public String getFirstName()
    {
        return firstName;
    }

    /**
     * Sets the user's first name.
     * @param firstName the first name to set
     */
    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

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