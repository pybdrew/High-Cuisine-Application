package com.menu.model;

public class HomeModel
{
    private String name;
    private String description;
    private String imageUrl;
    private String productType;

    //Constructor
    public HomeModel(String name, String description, String imageUrl, String productType)
    {
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.productType = productType;
    }

    // Getters and Setters
    public String getProductType() 
    {
        return productType;
    }

    public void setProductType(String productType) 
    {
        this.productType = productType;
    }


    public String getImageUrl()
    {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl)
    {
        this.imageUrl = imageUrl;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

}
