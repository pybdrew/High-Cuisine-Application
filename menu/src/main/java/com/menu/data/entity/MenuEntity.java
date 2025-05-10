package com.menu.data.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * Entity class representing a menu item stored in the MENUS table.
 * 
 * Each menu item has a name, description, image URL, and type (e.g., Drink or Sandwich).
 */
@Table("MENUS")  
public class MenuEntity
{
    /** Unique identifier for the menu item (primary key). */
    @Id
    private Long id;  

    /** Name of the menu item. */
    @Column("NAME")
    private String name;

    /** Description of the menu item. */
    @Column("DESCRIPTION")
    private String description;

    /** URL to the image representing the menu item. */
    @Column("IMAGE_URL")
    private String imageUrl;

    /** Category or type of the menu item (e.g., "Drink", "Sandwich"). */
    @Column("TYPE")
    private String type;

    /**
     * Non-default constructor used for initializing all fields.
     *
     * @param name name of the menu item
     * @param description description of the menu item
     * @param imageUrl image URL of the menu item
     * @param type type/category of the menu item
     */
    public MenuEntity(String name, String description, String imageUrl, String type)
    {
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.type = type;
    }

    /** Default constructor (required for Spring Data JDBC). */
    public MenuEntity() {}

    // Getters and setters

    /**
     * Gets the ID of the menu item.
     * @return the ID
     */
    public Long getId()
    {
        return id;
    }

    /**
     * Sets the ID of the menu item.
     * @param id the ID to set
     */
    public void setId(Long id)
    {
        this.id = id;
    }

    /**
     * Gets the name of the menu item.
     * @return the name
     */
    public String getName()
    {
        return name;
    }

    /**
     * Sets the name of the menu item.
     * @param name the name to set
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * Gets the description of the menu item.
     * @return the description
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * Sets the description of the menu item.
     * @param description the description to set
     */
    public void setDescription(String description)
    {
        this.description = description;
    }

    /**
     * Gets the image URL of the menu item.
     * @return the image URL
     */
    public String getImageUrl()
    {
        return imageUrl;
    }

    /**
     * Sets the image URL of the menu item.
     * @param imageUrl the image URL to set
     */
    public void setImageUrl(String imageUrl)
    {
        this.imageUrl = imageUrl;
    }

    /**
     * Gets the type of the menu item (e.g., Drink, Sandwich).
     * @return the type
     */
    public String getType()
    {
        return type;
    }

    /**
     * Sets the type of the menu item.
     * @param type the type to set
     */
    public void setType(String type)
    {
        this.type = type;
    }
}