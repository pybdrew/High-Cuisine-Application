package com.menu.data.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * Entity class representing a user stored in the USERS table.
 * 
 * Contains basic user account details such as username, password, role, and full name.
 */
@Table("USERS")
public class UserEntity
{
    /** Unique identifier for the user (primary key). */
    @Id
    Long id;

    /** Username used for login and identification. */
    @Column("USERNAME")
    String username;

    /** Encrypted password for authentication. */
    @Column("PASSWORD")
    String password;

    /** User's first name. */
    @Column("FIRST_NAME")
    String firstName;

    /** User's last name. */
    @Column("LAST_NAME")
    String lastName;

    /** Role of the user (e.g., admin, staff, customer). */
    @Column("ROLE")
    String role;

    /**
     * Constructor used to create a user with basic information.
     *
     * @param id the user ID
     * @param username the login username
     * @param password the encrypted password
     * @param role the role assigned to the user
     */
    public UserEntity(Long id, String username, String password, String role)
    {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    /** Default constructor required for Spring Data. */
    public UserEntity() {}

    // Getters and Setters

    /**
     * Gets the user ID.
     * @return the ID
     */
    public Long getId()
    {
        return id;
    }

    /**
     * Sets the user ID.
     * @param id the ID to set
     */
    public void setId(Long id)
    {
        this.id = id;
    }

    /**
     * Gets the username.
     * @return the username
     */
    public String getUsername()
    {
        return username;
    }

    /**
     * Sets the username.
     * @param username the username to set
     */
    public void setUsername(String username)
    {
        this.username = username;
    }

    /**
     * Gets the password.
     * @return the password
     */
    public String getPassword()
    {
        return password;
    }

    /**
     * Sets the password.
     * @param password the password to set
     */
    public void setPassword(String password)
    {
        this.password = password;
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
     * Gets the role of the user.
     * @return the role
     */
    public String getRole()
    {
        return role;
    }

    /**
     * Sets the role of the user.
     * @param role the role to set
     */
    public void setRole(String role)
    {
        this.role = role;
    }
}