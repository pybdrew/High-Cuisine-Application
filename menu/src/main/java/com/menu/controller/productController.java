package com.menu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.menu.model.ProductModel;

@Controller
@RequestMapping("/product")
public class productController 
{

    @GetMapping("/create")
    public String showCreateProductForm(Model model)
    {
        model.addAttribute("product", new ProductModel());
        return "product/createProduct";
    }


    @PostMapping("/create")
    public String createProduct(@ModelAttribute("product") ProductModel product)
    {
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
