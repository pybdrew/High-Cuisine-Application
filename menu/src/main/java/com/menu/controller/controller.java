package com.menu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.menu.model.LoginModel;

import jakarta.validation.Valid;


@Controller
public class controller
{

    /**
     * Mapping for homepage
     * @param model
     * @return
     */
    @GetMapping("/home")
    public String home(Model model)
    {
        model.addAttribute("title", "Home");
        model.addAttribute("content", "Hello, welcome to the homepage!");
        return "home";
    }

    /**
     * Mapping for login
     * @param model
     * @return
     */
    @GetMapping("/login")
    public String login(Model model)
    {
        model.addAttribute("loginModel", new LoginModel());
        return "login";
    }

    /**
     * Check fields for login
     * @param loginModel
     * @param bindingResult
     * @param model
     * @return
     */
    @PostMapping("/doLogin")
    public String doLogin(@Valid LoginModel loginModel, BindingResult bindingResult, Model model)
    {
        System.out.println(String.format("Form with username of %s and Password of %s", loginModel.getUsername(), loginModel.getPassword()));

        if (bindingResult.hasErrors())
        {
            model.addAttribute("title", "Login Form");
            return "login";
        }

        // Redirect to homepage after successful login
        return "redirect:/home";
    }

    /**
     * Mapping for register
     * @param model
     * @return
     */
    @GetMapping("/register")
    public String register(Model model)
    {
        model.addAttribute("title", "Registration");
        return "register";
    }

    @GetMapping("/drink")
public String showDrinkMenu() {
    return "drinks"; // maps to drinks.html
}

@GetMapping("/sandwich")
public String showSandwichMenu() {
    return "sandwich"; // maps to sandwich.html
}
    
}

