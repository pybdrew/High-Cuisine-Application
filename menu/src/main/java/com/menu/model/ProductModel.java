package com.menu.model;

public class ProductModel 
{

    private String name;
    private String description;
    private String imageUrl;
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
