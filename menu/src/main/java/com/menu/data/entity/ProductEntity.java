package com.menu.data.entity;

import java.util.Base64;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("PRODUCTS")
public class ProductEntity {

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

    // This field is not persisted in the database
    @Transient
    public String getBase64Image() {
        if (imageData != null && imageData.length > 0) {
            return "data:image/jpeg;base64," + Base64.getEncoder().encodeToString(imageData);
        }
        return null;
    }

    // Constructors
    public ProductEntity() {}

    public ProductEntity(Long id, String name, String description, String type) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.type = type;
    }

    public ProductEntity(Long id, String name, String description, String imageUrl, String type, byte[] imageData) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.type = type;
        this.imageData = imageData;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public byte[] getImageData() {
        return imageData;
    }

    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }
}