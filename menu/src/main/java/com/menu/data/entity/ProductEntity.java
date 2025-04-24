package com.menu.data.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

// Represents product entity mapped to 'PRODUCTS' table in database
@Table("PRODUCTS")
public class ProductEntity
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

    @Column("IMAGE_DATA")
private byte[] imageData;

public byte[] getImageData() {
    return imageData;
}

public void setImageData(byte[] imageData) {
    this.imageData = imageData;
}

    // Non-default constructor
    public ProductEntity(Long id, String name, String description, String type)
    {
        this.id = id;
        this.name = name;
        this.description = description;
        this.type = type;
    }

    // Default constructor
    public ProductEntity() {}

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
