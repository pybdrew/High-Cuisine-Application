package com.menu.model;

public class DrinkModel
{
    private String name;
    private String description;
    private String imageUrl;
    
    //Constructor
    public DrinkModel(String name, String description, String imageUrl)
    {
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
    }
    public String getImageUrl()
    {
        return imageUrl;
    }
    public void setImageUrl(String imageUrl)
    {
        this.imageUrl = imageUrl;
    }
    public String getDescription() {
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