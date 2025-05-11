package com.menu.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.menu.data.entity.MenuEntity;
import com.menu.data.entity.ProductEntity;
import com.menu.data.repository.*;
import com.menu.model.*;
import com.menu.service.*;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

/**
 * Main controller for the High Cruise application.
 * 
 * Handles public routes such as home, login, registration,
 * and customer-facing menu pages.
 */
@Controller
public class controller
{
    private final ProductsRepository productsRepository;
    private final MenuRepository menuRepository;
    private final LoginService loginService;
    private final RegisterService registerService;
    private final PasswordEncoder passwordEncoder;

    /**
     * Constructor for dependency injection.
     *
     * @param productsRepository the repository for product data
     * @param menuRepository the repository for menu data
     * @param loginService the service for user login
     * @param registerService the service for user registration
     * @param passwordEncoder the encoder for securing passwords
     */
    public controller(ProductsRepository productsRepository, MenuRepository menuRepository, LoginService loginService, RegisterService registerService, PasswordEncoder passwordEncoder)
    {
        this.productsRepository = productsRepository;
        this.menuRepository = menuRepository;
        this.loginService = loginService;
        this.registerService = registerService;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Displays the home page with available menus.
     *
     * @param model the model to pass data to the view
     * @return the home page template
     */
    @GetMapping("/")
    public String home(Model model)
    {
        Iterable<MenuEntity> menuEntities = menuRepository.findAll();
        List<HomeModel> menus = new ArrayList<>();
        for (MenuEntity entity : menuEntities)
        {
            menus.add(new HomeModel(
                entity.getName(),
                entity.getDescription(),
                entity.getImageUrl(),
                entity.getType()
            ));
        }

        model.addAttribute("menus", menus);
        model.addAttribute("title", "Home");
        return "home";
    }

    /**
     * Displays the login page.
     *
     * @param model the model to pass data to the view
     * @return the login page template
     */
    @GetMapping("/login")
    public String login(Model model)
    {
        model.addAttribute("loginModel", new LoginModel());
        return "login";
    }

    /**
     * Logs the user out by invalidating the session.
     *
     * @param session the HTTP session to invalidate
     * @return redirect to login page
     */
    @GetMapping("/logout")
    public String logout(HttpSession session)
    {
        session.invalidate();
        return "redirect:/login";
    }

    /**
     * Displays the registration page.
     *
     * @param model the model to pass data to the view
     * @return the registration form template
     */
    @GetMapping("/register")
    public String register(Model model)
    {
        model.addAttribute("title", "Registration");
        model.addAttribute("registerModel", new RegisterModel());
        return "register";
    }

    /**
     * Handles user registration.
     *
     * @param registerModel the form model for registration
     * @param bindingResult validation results
     * @param model the model to pass data to the view
     * @return redirect or registration page if errors occur
     */
    @PostMapping("/doRegister")
    public String doRegister(@Valid RegisterModel registerModel, BindingResult bindingResult, Model model)
    {
        System.out.printf(
            "Form with First name: %s, Last name: %s, Username: %s, and Password: %s%n",
            registerModel.getFirstName(),
            registerModel.getLastName(),
            registerModel.getUsername(),
            registerModel.getPassword()
        );

        if (bindingResult.hasErrors())
        {
            model.addAttribute("title", "Registration");
            return "register";
        }

        String encodedPassword = passwordEncoder.encode(registerModel.getPassword());
        boolean success = registerService.registerUser(registerModel, encodedPassword);

        if (!success)
        {
            model.addAttribute("registerError", "Registration failed. Try again.");
            return "register";
        }

        return "redirect:/";
    }

    /**
     * Displays the admin home page if user is authorized.
     *
     * @param model the model to pass data to the view
     * @param session the current user session
     * @return the admin home page or login redirect
     */
    @GetMapping("/adminHome")
    public String adminHomePage(Model model, HttpSession session)
    {
        if (session.getAttribute("userRole") == null || !session.getAttribute("userRole").equals("admin"))
        {
            return "redirect:/login";
        }

        model.addAttribute("title", "Admin Home");
        return "adminHome";
    }

    /**
     * Displays the drink menu page.
     *
     * @param model the model to pass drink data to the view
     * @return the drinks page template
     */
    @GetMapping("/drink")
    public String drinkMenu(Model model)
    {
        List<ProductEntity> drinkEntities = productsRepository.findByType("Drink");
        List<ProductModel> drinks = new ArrayList<>();

        for (ProductEntity entity : drinkEntities)
        {
            drinks.add(new ProductModel(
                entity.getId(), 
                entity.getName(),
                entity.getDescription(),
                entity.getImageUrl(),
                entity.getType()
            ));
        }

        model.addAttribute("title", "Drink Menu");
        model.addAttribute("drinks", drinks);
        return "drinks";
    }

    /**
     * Displays the sandwich menu page.
     *
     * @param model the model to pass sandwich data to the view
     * @return the sandwich menu template
     */
    @GetMapping("/sandwich")
    public String sandwichMenu(Model model)
    {
        List<ProductEntity> sandwichEntities = productsRepository.findByType("Sandwich");
        List<ProductModel> sandwiches = new ArrayList<>();

        for (ProductEntity entity : sandwichEntities)
        {
            sandwiches.add(new ProductModel(
                entity.getId(),
                entity.getName(),
                entity.getDescription(),
                entity.getImageUrl(),
                entity.getType()
            ));
        }

        model.addAttribute("title", "Sandwich Menu");
        model.addAttribute("sandwiches", sandwiches);
        return "sandwiches";
    }
}