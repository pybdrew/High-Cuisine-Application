package com.menu.data.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

// Represents user entity mapped to 'USERS' in database
@Table("USERS")
public class UserEntity
{
    @Id
    Long id;

    @Column("USERNAME")
    String username;

    @Column("PASSWORD")
    String password;

    @Column("FIRST_NAME")
    String firstName;

    @Column("LAST_NAME")
    String lastName;

    @Column("ROLE")
    String role;

    // Non-default constructor
    public UserEntity(Long id, String username, String password, String role)
    {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    // Default constructor
    public UserEntity() {}

    // Getters and Setters
    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public String getRole()
    {
        return role;
    }

    public void setRole(String role)
    {
        this.role = role;
    }
}
