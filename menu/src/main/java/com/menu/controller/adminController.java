package com.menu.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.menu.data.entity.*;
import com.menu.model.*;
import com.menu.service.*;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/admin")
public class adminController
{

    private final ProductService productService;
    private final LoginService loginService;
    private final MenuService menuService;

    /**
     * Constructor-based injection
     * @param productService
     * @param loginService
     * @param menuService
     */
    public adminController(ProductService productService, LoginService loginService, MenuService menuService)
    {
        this.productService = productService;
        this.loginService = loginService;
        this.menuService = menuService;
    }

    /**
     * Admin Page
     */
    @GetMapping("")
    public String homePage(Model model)
    {
        return "admin/home";
    }

    //-------- Product Management
     /**
     * Admin Page
     */
    @GetMapping("/product")
    public String showProductManagementPage(Model model)
    {
        List<ProductEntity> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "/admin/product/productManagement";
    }

    /**
     * Create new products page handler
     */
    @GetMapping("/product/create")
    public String showCreateProductForm(Model model)
    {
        model.addAttribute("product", new ProductModel());
        return "admin/product/createProduct";
    }


    /**
     * Update menu with new product and redirect user appropriately
     * @param product
     * @param bindingResult
     * @param model
     * @return
     */
    @PostMapping("/product/create")
    public String createProduct(@ModelAttribute("product") @Valid ProductModel product, BindingResult bindingResult, Model model)
    {
        if (bindingResult.hasErrors())
        {
            return "admin/product/create";
        }

        productService.createProduct(product);
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

    /**
     * Delete product by ID
     * @param productId
     * @return
     */
    @GetMapping("/product/delete/{id}")
    public String deleteProduct(@PathVariable("id") Long productId)
    {
        productService.deleteProduct(productId);
        return "redirect:/admin";
    }

    /**
     * Displays edit product page for specific product identified by given ID
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/product/edit/{id}")
    public String showEditProductPage(@PathVariable("id") Long id, Model model)
    {
        ProductEntity product = productService.getProductById(id);
        if (product != null)
        {
            model.addAttribute("product", product);
            return "admin/product/editProduct";
        }
        else
        {
            return "redirect:/admin";
        }
    }

    /**
     * Handles updates (edits) to products
     * @param id
     * @param product
     * @return
     */
    @PostMapping("/product/edit/{id}")
    public String updateProduct(@PathVariable("id") Long id, @ModelAttribute ProductEntity product)
    {
        product.setId(id);
        productService.updateProduct(product);
        return "redirect:/admin/product";
    }

    /**
     * Displays user management page
     */
    @GetMapping("/users")
    public String showUserManagement(Model model)
    {
        List<UserEntity> users = loginService.getAllUsers();
        model.addAttribute("users", users);
        return "admin/users/userManagement";
    }


    /**
     * Display create user page 
     */
    @GetMapping("/users/create")
    public String showCreateUserPage(Model model)
    {
    model.addAttribute("registerModel", new RegisterModel());
    model.addAttribute("roles", List.of("admin", "staff", "customer")); // Add this
    return "admin/users/createUser";
    }

    /**
     * Controls action after submitting new user creation
     * @param registerModel
     * @param bindingResult
     * @param role
     * @param model
     * @return
     */
    @PostMapping("/user/create")
    public String createUser(@ModelAttribute("registerModel") @Valid RegisterModel registerModel, BindingResult bindingResult, @RequestParam("role") String role, Model model)
    {
        if (bindingResult.hasErrors())
        {
            // needed to repopulate select
            model.addAttribute("roles", List.of("admin", "staff", "customer"));
            return "admin/users/createUser";
        }
        loginService.saveUser(registerModel, role);
        return "redirect:/admin/users";
    }

    //----------- User Management
    /**
     * Display user information for admin to edit
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/user/edit/{id}")
    public String showEditUserForm(@PathVariable Long id, Model model)
    {
        UserEntity user = loginService.getUserById(id);
        if (user == null)
        {
            return "redirect:/admin/users";
        }

        RegisterModel registerModel = new RegisterModel();
        registerModel.setUsername(user.getUsername());
        registerModel.setPassword(user.getPassword());
        registerModel.setFirstName(user.getFirstName());
        registerModel.setLastName(user.getLastName());

        model.addAttribute("registerModel", registerModel);
        model.addAttribute("userId", id);
        model.addAttribute("currentRole", user.getRole());
        // Not part of RegisterModel
        model.addAttribute("roles", List.of("admin", "staff", "customer"));
        return "admin/users/editUser";
    }

    /**
     * Controls action when submitting updated user information
     * @param id
     * @param registerModel
     * @param role
     * @param bindingResult
     * @param model
     * @return
     */
    @PostMapping("/user/edit/{id}")
    public String updateUser(@PathVariable Long id, @ModelAttribute("registerModel") @Valid RegisterModel registerModel, @RequestParam("role") String role, BindingResult bindingResult,Model model)
    {
        if (bindingResult.hasErrors())
        {
            model.addAttribute("roles", List.of("admin", "staff", "customer"));
            return "admin/users/editUser";
        }

        loginService.updateUser(id, registerModel, role);
        return "redirect:/admin/users";
    }

    @GetMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable Long id)
    {
        loginService.deleteUser(id);
        return "redirect:/admin/users";
    }

    //-------- Menu Management
    
    // Menu Management Page
    @GetMapping("/menus")
    public String showMenuManagementPage(Model model)
    {
        Iterable<MenuEntity> menus = menuService.getAllMenus();
        model.addAttribute("menus", menus);
        return "admin/menus/menuManagement";
    }

    // Create new menu page handler
    @GetMapping("/menus/create")
    public String showCreateMenuForm(Model model)
    {
        model.addAttribute("menu", new HomeModel(null, null, null, null));
        return "admin/menus/createMenu";
    }

    /**
     * Post handler for creating new menu
     * @param menu
     * @param bindingResult
     * @return
     */
    @PostMapping("/menus/create")
    public String createMenu(@ModelAttribute("menu") @Valid HomeModel menu, BindingResult bindingResult)
    {
        if (bindingResult.hasErrors())
        {
            return "admin/menus/createMenu";
        }

        menuService.createMenu(menu); 
        return "redirect:/admin/menus"; 
    }

    // Delete menu by ID
    @GetMapping("/menus/delete/{id}")
    public String deleteMenu(@PathVariable("id") Long menuId)
    {
        menuService.deleteMenu(menuId);
        return "redirect:/admin/menus";
    }

    // Edit menu page handler
    @GetMapping("/menus/edit/{id}")
    public String showEditMenuForm(@PathVariable("id") Long id, Model model)
    {
        MenuEntity menu = menuService.getMenuById(id);
        if (menu != null)
        {
            model.addAttribute("menu", menu);
            return "admin/menus/editMenu";
        }
        else
        {
            return "redirect:/admin/menus";
        }
    }

    @PostMapping("/menus/edit/{id}")
    public String updateMenu(@PathVariable("id") Long id, @ModelAttribute MenuEntity menu)
    {
        System.out.println("Updating menu with ID: " + id);
        menu.setId(id); // Ensure the ID is set for updating
        menuService.updateMenu(menu); // Save the updated menu
        System.out.println("Menu updated successfully, redirecting to menu management");
        return "redirect:/admin/menus"; 
    }
}
