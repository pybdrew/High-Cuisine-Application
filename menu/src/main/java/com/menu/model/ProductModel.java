package com.menu.model;

import jakarta.validation.constraints.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * A model class used for creating and editing product entries.
 * 
 * This class represents a form-backed object that captures product details,
 * including name, description, type, and an optional image file or URL.
 */
public class ProductModel {

    /** The unique identifier for the product (used during editing). */
    private Long id;

    /**
     * Gets the product ID.
     * @return the product ID
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

    /** The uploaded image file for the product (optional). */
    private MultipartFile imageFile;

    /**
     * Gets the uploaded image file.
     * @return the image file
     */
    public MultipartFile getImageFile() {
        return imageFile;
    }

    /**
     * Sets the uploaded image file.
     * @param imageFile the file to set
     */
    public void setImageFile(MultipartFile imageFile) {
        this.imageFile = imageFile;
    }

    /** The name of the product. Must be between 1 and 100 characters. */
    @NotNull(message = "Product name is a required field")
    @Size(min = 1, max = 100, message = "Product name must be between 1 and 100 characters")
    private String name;

    /** The description of the product. Must be between 10 and 500 characters. */
    @NotNull(message = "Description is a required field")
    @Size(min = 10, max = 500, message = "Description must be between 10 and 500 characters")
    private String description;

    /** An optional image URL for the product. */
    private String imageUrl;

    /** The type/category of the product. Must be "Sandwich", "Drink", or "Snack". */
    @NotNull(message = "Product type is a required field")
    @Pattern(
        regexp = "^(Sandwich|Drink|Snack)$",
        message = "Product type must be either Sandwich, Drink, or Snack"
    )
    private String productType;

    /** Default constructor. */
    public ProductModel() {}

    /**
     * Full constructor with key fields (used for editing).
     *
     * @param id the product ID
     * @param name the product name
     * @param description the product description
     * @param imageUrl the image URL
     * @param productType the product type
     */
    public ProductModel(Long id, String name, String description, String imageUrl, String productType) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.productType = productType;
    }

    /**
     * Returns a string representation of the product.
     * @return string with key product details
     */
    @Override
    public String toString() {
        return "ProductModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", productType='" + productType + '\'' +
                '}';
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
     * Gets the product image URL.
     * @return the image URL
     */
    public String getImageUrl() {
        return imageUrl;
    }

    /**
     * Sets the product image URL.
     * @param imageUrl the image URL to set
     */
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    /**
     * Gets the product type (e.g., "Sandwich", "Drink", "Snack").
     * @return the product type
     */
    public String getProductType() {
        return productType;
    }

    /**
     * Sets the product type.
     * @param productType the type to set
     */
    public void setProductType(String productType) {
        this.productType = productType;
    }
}