package com.menu.data.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

// Mapped to MENUS in MySQL
@Table("MENUS")  
public class MenuEntity
{

    @Id
    private Long id;  

    @Column("NAME")
    private String name;

    @Column("DESCRIPTION")
    private String description;

    @Column("IMAGE_URL")
    private String imageUrl;

    @Column("TYPE")
    private String type;

    // Non-default constructor 
    public MenuEntity(String name, String description, String imageUrl, String type)
    {
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.type = type;
    }

    // Default constructor 
    public MenuEntity() {}

    // Getters and Setters
    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getImageUrl()
    {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl)
    {
        this.imageUrl = imageUrl;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }
}
