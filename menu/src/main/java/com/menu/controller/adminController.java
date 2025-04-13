package com.menu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.menu.data.entity.ProductEntity;
import com.menu.model.ProductModel;
import com.menu.service.ProductService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/product")
public class adminController
{

    // Autowired Section
    private final ProductService productService;

    @Autowired
    public adminController(ProductService productService)
    {
        this.productService = productService;
    }

    /**
     * Admin Page
     * @param model
     * @return
     */
    @GetMapping("")
    public String showProductManagementPage(Model model)
    {
        // Get list of products from database
        List<ProductEntity> products = productService.getAllProducts();
        
        // Add the products to the model so they can be accessed 
        model.addAttribute("products", products);
        return "product/productManagement";
    }

    /**
     * Create new products page handler
     * @param model
     * @return
     */
    @GetMapping("/create")
    public String showCreateProductForm(Model model)
    {
        model.addAttribute("product", new ProductModel());
        return "product/createProduct";
    }

    /**
     * Update menu with new product and redirect user appropriately
     * @param product
     * @param bindingResult
     * @param model
     * @return
     */
    @PostMapping("/create")
    public String createProduct(@ModelAttribute("product") @Valid ProductModel product, BindingResult bindingResult, Model model)
    {
        // Return page to display errors
        if (bindingResult.hasErrors())
        {
            return "product/createProduct";
        }
        
        productService.createProduct(product);

        System.out.println("Product created: " + product);

        // Redirect to appropriate page upon creation
        if ("Drink".equalsIgnoreCase(product.getProductType()))
        {
            return "redirect:/drink";
        }
        else if ("Sandwich".equalsIgnoreCase(product.getProductType()))
        {
            return "redirect:/sandwich";
        }
        else
        {
            return "redirect:/";
        }
    }

    /**
     * Delete product by ID
     * @param productId
     * @return
     */
    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") Long productId)
    {
        productService.deleteProduct(productId);
        return "redirect:/product";
    }

    /**
     * Displays edit product page for specific product identified by given ID
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/edit/{id}")
    public String showEditProductPage(@PathVariable("id") Long id, Model model)
    {
        ProductEntity product = productService.getProductById(id);
        if (product != null)
        {
            model.addAttribute("product", product);
            return "product/editProduct"; 
        } 
        else
        {
            return "redirect:/product"; 
        }
    }

    /**
     * Handles updates (edits) to products
     */
    @PostMapping("/edit/{id}")
    public String updateProduct(@PathVariable("id") Long id, @ModelAttribute ProductEntity product)
    {
        // Set the product's ID to make sure we update the correct one
        product.setId(id);  
        productService.updateProduct(product);
        // Redirect to management page after updating
        return "redirect:/product"; 
    }
}
