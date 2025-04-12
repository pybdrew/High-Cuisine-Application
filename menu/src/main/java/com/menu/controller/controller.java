package com.menu.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.menu.data.entity.ProductEntity;
import com.menu.data.repository.ProductsRepository;
import com.menu.model.*;
import com.menu.service.*;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;



@Controller
public class controller
{

    // Autowire Section
    @Autowired
    private ProductsRepository productsRepository;

    private final LoginService loginService;
    private final RegisterService registerService;
    
    public controller(LoginService loginService, RegisterService registerService) 
    {
        this.loginService = loginService;
        this.registerService = registerService;
    }

    /**
     * Mapping for homepage
     * @param model
     * @return
     */
    @GetMapping("/")
    public String home(Model model)
    {
        // TODO - DB connect 
        List<HomeModel> menus = new ArrayList<>();
        menus.add(new HomeModel("Drinks", "Warm up with our curated selection of hot and cold beverages.",
        "https://images.unsplash.com/photo-1509042239860-f550ce710b93?auto=format&fit=crop&w=800&q=80", "drink"));
        menus.add(new HomeModel("Sandwiches", "Explore our handcrafted sandwiches made with the freshest ingredients.",
        "https://images.unsplash.com/photo-1586190848861-99aa4a171e90?ixlib=rb-4.0.3&auto=format&fit=crop&w=800&q=80", "sandwich"));

        model.addAttribute("menus", menus);
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
    public String doLogin(@Valid LoginModel loginModel, BindingResult bindingResult, Model model, HttpSession session)
    {
        System.out.println(String.format("Form with username of %s and Password of %s", loginModel.getUsername(), loginModel.getPassword()));

        // If there are validation errors return to login page
        if (bindingResult.hasErrors())
        {
            model.addAttribute("title", "Login Form");
            return "login";
        }

        // Call LoginService to validate username and password
        String role = loginService.validateLogin(loginModel.getUsername(), loginModel.getPassword());

        // If no matching user is found show error message
        if (role == null)
        {
            model.addAttribute("error", "Account not registered, please register.");
            return "login";
        }

        // Store user role in session if login successful
        session.setAttribute("userRole", role);

        // Redirect based on user role (Admin or regular user)
        if (role.equals("admin"))
        {
            return "redirect:/adminHome";
        }
        else
        {
            return "redirect:/";
        }
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
        model.addAttribute("registerModel", new RegisterModel());
        return "register";
    }

    /**
     * Handles Registrations
     * @param registerModel
     * @param bindingResult
     * @param model
     * @return
     */
    @PostMapping("/doRegister")
    public String doRegister(@Valid RegisterModel registerModel, BindingResult bindingResult, Model model)
    {
        // Print information to console
        System.out.println(String.format(
            "Form with First name: %s, Last name: %s, Username: %s, and Password: %s",
            registerModel.getFirstName(),
            registerModel.getLastName(),
            registerModel.getUsername(),
            registerModel.getPassword()));
    
        // Return page to show errors
        if (bindingResult.hasErrors())
        {
            model.addAttribute("title", "Registration");
            return "register";
        }
    
        boolean success = registerService.registerUser(registerModel);
    

        if (!success)
        {
            model.addAttribute("registerError", "Registration failed. Try again.");
            return "register";
        }
    
        return "redirect:/";
    }
    

    /**
     * Gets admin to admin page
     * @param model
     * @param session
     * @return
     */
    @GetMapping("/adminHome")
    public String adminHomePage(Model model, HttpSession session)
    {
        // Checks if user logged in and has admin role
        if (session.getAttribute("userRole") == null || !session.getAttribute("userRole").equals("admin"))
        {
            return "redirect:/login";
        }

        model.addAttribute("title", "Admin Home");
        return "adminHome";
    }


    /**
     * Mapping for drink menu
     * @return
     */
    @GetMapping("/drink")
    public String drinkMenu(Model model)
    {
        // Fetch all drink products from the database
        List<ProductEntity> drinkEntities = productsRepository.findByType("Drink");
    
        // Map to ProductModel
        List<ProductModel> drinks = new ArrayList<>();
        for (ProductEntity entity : drinkEntities)
        {
            // Ensure the URL, name, description, and type are set in the ProductModel
            ProductModel productModel = new ProductModel
            (
                entity.getName(),
                entity.getDescription(),
                entity.getImageUrl(),
                entity.getType()
            );
            drinks.add(productModel);
        }
    
        model.addAttribute("title", "Drink Menu");
        model.addAttribute("drinks", drinks);
        return "drinks";
    }

    /**
     * Mapping for food menu(currently sandwiches)
     * @return
     */
    @GetMapping("/sandwich")
    public String sandwichMenu(Model model)
    {
        // Get sandwiches from database
        List<ProductEntity> sandwichEntities = productsRepository.findByType("Sandwich");
    
        // Map to ProductModel
        List<ProductModel> sandwiches = new ArrayList<>();
        for (ProductEntity entity : sandwichEntities)
        {
            ProductModel productModel = new ProductModel
            (
                entity.getName(),
                entity.getDescription(),
                entity.getImageUrl(),
                entity.getType()
            );
            sandwiches.add(productModel);
        }
    
        model.addAttribute("title", "Sandwich Menu");
        model.addAttribute("sandwiches", sandwiches);
        return "sandwiches";
    }
}

