package com.menu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.menu.model.ProductModel;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/product")
public class adminController
{
    @GetMapping("")
    public String showProductManagementPage()
    {
        return "product/productManagement";
    }

    @GetMapping("/create")
    public String showCreateProductForm(Model model)
    {
        model.addAttribute("product", new ProductModel());
        return "product/createProduct";
    }

    @PostMapping("/create")
    public String createProduct(@ModelAttribute("product") @Valid ProductModel product, BindingResult bindingResult, Model model)
    {
        if (bindingResult.hasErrors())
        {
            return "product/createProduct";
        }

        System.out.println("Product created: " + product);

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
}
