package com.menu.data.entity;

import java.util.Base64;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * Entity class representing a product in the PRODUCTS table.
 * 
 * Each product may include an image (stored as URL or binary),
 * a name, a description, and a type (e.g., Drink, Sandwich).
 */
@Table("PRODUCTS")
public class ProductEntity {

    /** Unique identifier for the product (primary key). */
    @Id
    private Long id;

    /** Name of the product. */
    @Column("NAME")
    private String name;

    /** Description of the product. */
    @Column("DESCRIPTION")
    private String description;

    /** Optional image URL pointing to a hosted image. */
    @Column("IMAGE_URL")
    private String imageUrl;

    /** Type/category of the product (e.g., "Drink", "Sandwich"). */
    @Column("TYPE")
    private String type;

    /** Binary image data stored in the database. */
    @Column("IMAGE_DATA")
    private byte[] imageData;

    /**
     * Returns the image data encoded as a Base64 string for embedding in HTML.
     * 
     * @return the base64-encoded JPEG image string, or null if no image data exists
     */
    @Transient
    public String getBase64Image() {
        if (imageData != null && imageData.length > 0) {
            return "data:image/jpeg;base64," + Base64.getEncoder().encodeToString(imageData);
        }
        return null;
    }

    /** Default constructor. */
    public ProductEntity() {}

    /**
     * Constructor with basic fields (excluding image URL and binary image).
     *
     * @param id the product ID
     * @param name the product name
     * @param description the product description
     * @param type the product type
     */
    public ProductEntity(Long id, String name, String description, String type) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.type = type;
    }

    /**
     * Full constructor with image support.
     *
     * @param id the product ID
     * @param name the product name
     * @param description the product description
     * @param imageUrl optional image URL
     * @param type the product type
     * @param imageData binary image data
     */
    public ProductEntity(Long id, String name, String description, String imageUrl, String type, byte[] imageData) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.type = type;
        this.imageData = imageData;
    }

    // Getters and Setters

    /**
     * Gets the product ID.
     * @return the ID
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the product ID.
     * @param id the ID to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the product name.
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the product name.
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the product description.
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the product description.
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the image URL.
     * @return the image URL
     */
    public String getImageUrl() {
        return imageUrl;
    }

    /**
     * Sets the image URL.
     * @param imageUrl the image URL to set
     */
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    /**
     * Gets the product type (e.g., Drink or Sandwich).
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the product type.
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Gets the binary image data.
     * @return the image data
     */
    public byte[] getImageData() {
        return imageData;
    }

    /**
     * Sets the binary image data.
     * @param imageData the image data to set
     */
    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }
}