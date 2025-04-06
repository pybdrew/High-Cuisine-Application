package com.menu.model;

import jakarta.validation.constraints.*;

public class ProductModel
{

    @NotNull(message = "Product name is a required field")
    @Size(min = 1, max = 100, message = "Product name must be between 1 and 100 characters")
    private String name;

    @NotNull(message = "Description is a required field")
    @Size(min = 10, max = 500, message = "Description must be between 10 and 500 characters")
    private String description;

    @NotNull(message = "Image URL is a required field")
    @Pattern(regexp = "^(http|https)://.*\\.(jpg|jpeg|png|gif|bmp)$", message = "Invalid image URL format")
    private String imageUrl;

    @NotNull(message = "Product type is a required field")
    @Pattern(regexp = "^(Sandwich|Drink|Snack)$", message = "Product type must be either Sandwich, Drink, or Snack")
    private String productType;

    public ProductModel() {}

    public ProductModel(String name, String description, String imageUrl, String productType)
    {
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.productType = productType;
    }

    @Override
    public String toString()
    {
        return "ProductModel{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", productType='" + productType + '\'' +
                '}';
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

    public String getProductType()
    {
        return productType;
    }

    public void setProductType(String productType)
    {
        this.productType = productType;
    }
}
