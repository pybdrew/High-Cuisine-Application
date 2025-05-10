package com.menu.model;

/**
 * A model class used to represent menu items displayed on the home page.
 * 
 * This class holds non-persistent data such as the name, description,
 * image URL, and product type (e.g., Drink, Sandwich) for frontend display.
 */
public class HomeModel
{
    /** Name of the menu item. */
    private String name;

    /** Description of the menu item. */
    private String description;

    /** URL of the image associated with the menu item. */
    private String imageUrl;

    /** Product type/category (e.g., "Drink", "Sandwich"). */
    private String productType;

    /**
     * Constructs a new {@code HomeModel} with all properties.
     *
     * @param name the name of the menu item
     * @param description the description of the menu item
     * @param imageUrl the URL of the item's image
     * @param productType the category/type of the item
     */
    public HomeModel(String name, String description, String imageUrl, String productType)
    {
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.productType = productType;
    }

    /**
     * Gets the product type.
     * @return the product type
     */
    public String getProductType() 
    {
        return productType;
    }

    /**
     * Sets the product type.
     * @param productType the type to set
     */
    public void setProductType(String productType) 
    {
        this.productType = productType;
    }

    /**
     * Gets the image URL.
     * @return the image URL
     */
    public String getImageUrl()
    {
        return imageUrl;
    }

    /**
     * Sets the image URL.
     * @param imageUrl the URL to set
     */
    public void setImageUrl(String imageUrl)
    {
        this.imageUrl = imageUrl;
    }

    /**
     * Gets the description.
     * @return the description
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * Sets the description.
     * @param description the description to set
     */
    public void setDescription(String description)
    {
        this.description = description;
    }

    /**
     * Gets the name.
     * @return the name
     */
    public String getName()
    {
        return name;
    }

    /**
     * Sets the name.
     * @param name the name to set
     */
    public void setName(String name)
    {
        this.name = name;
    }
}