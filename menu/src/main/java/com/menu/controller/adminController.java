package com.menu.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.menu.data.entity.*;
import com.menu.data.repository.UsersRepository;
import com.menu.model.*;
import com.menu.service.*;

import jakarta.validation.Valid;

/**
 * Controller for admin-related functions in the High Cruise menu system.
 * 
 * Provides interfaces for managing products, users, and menus through the admin dashboard.
 */
@Controller
@RequestMapping("/admin")
public class adminController
{

    private final ProductService productService;
    private final LoginService loginService;
    private final MenuService menuService;

    /**
     * Constructor with service dependencies injected.
     */
    public adminController(ProductService productService, LoginService loginService, MenuService menuService)
    {
        this.productService = productService;
        this.loginService = loginService;
        this.menuService = menuService;
    }

    /**
     * Displays the admin home page.
     */
    @GetMapping("")
    public String homePage(Model model)
    {
        return "admin/home";
    }

    // -------- Product Management

    /**
     * Displays the product management page.
     */
    @GetMapping("/product")
    public String showProductManagementPage(Model model)
    {
        List<ProductEntity> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "/admin/product/productManagement";
    }

    /**
     * Shows the form to create a new product.
     */
    @GetMapping("/product/create")
    public String showCreateProductForm(Model model)
    {
        model.addAttribute("product", new ProductModel());
        return "admin/product/createProduct";
    }

    /**
     * Handles submission of the new product form.
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
     * Deletes a product by ID.
     */
    @GetMapping("/product/delete/{id}")
    public String deleteProduct(@PathVariable("id") Long productId)
    {
        productService.deleteProduct(productId);
        return "redirect:/admin";
    }

    /**
     * Displays the edit form for an existing product.
     */
    @GetMapping("/product/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model)
    {
        ProductEntity entity = productService.getProductById(id);
        ProductModel formModel = new ProductModel(
            entity.getId(),
            entity.getName(),
            entity.getDescription(),
            entity.getImageUrl(),
            entity.getType()
        );
        model.addAttribute("product", formModel);
        return "admin/product/editProduct";
    }

    /**
     * Updates the product after form submission.
     */
    @PostMapping("/product/edit/{id}")
    public String updateProduct(@PathVariable("id") Long id, @ModelAttribute("product") @Valid ProductModel productModel, BindingResult result, Model model)
    {
        if (result.hasErrors())
        {
            return "admin/product/editProduct";
        }

        productService.updateProduct(id, productModel);
        return "redirect:/admin/product";
    }

    /**
     * Serves the product image as a JPEG.
     */
    @GetMapping(value = "/product/image/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public ResponseEntity<byte[]> serveProductImage(@PathVariable Long id)
    {
        ProductEntity product = productService.getProductById(id);
        if (product != null && product.getImageData() != null)
        {
            return ResponseEntity.ok().body(product.getImageData());
        }
        else
        {
            return ResponseEntity.notFound().build();
        }
    }

    // -------- User Management

    /**
     * Displays the user management page.
     */
    @GetMapping("/users")
    public String showUserManagement(Model model)
    {
        List<UserEntity> users = loginService.getAllUsers();
        model.addAttribute("users", users);
        return "admin/users/userManagement";
    }

    /**
     * Displays the create user form.
     */
    @GetMapping("/users/create")
    public String showCreateUserPage(Model model)
    {
        model.addAttribute("registerModel", new RegisterModel());
        model.addAttribute("roles", List.of("admin", "staff", "user"));
        return "admin/users/createUser";
    }

    /**
     * Handles creation of a new user.
     */
    @PostMapping("/user/create")
    public String createUser(@ModelAttribute("registerModel") @Valid RegisterModel registerModel, BindingResult bindingResult, @RequestParam("role") String role, Model model)
    {
        if (bindingResult.hasErrors())
        {
            model.addAttribute("roles", List.of("admin", "staff", "user"));
            return "admin/users/createUser";
        }
        loginService.saveUser(registerModel, role);
        return "redirect:/admin/users";
    }

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
    * Displays the edit form for a user.
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
        registerModel.setFirstName(user.getFirstName());
        registerModel.setLastName(user.getLastName());

        model.addAttribute("registerModel", registerModel);
        model.addAttribute("userId", id);
        model.addAttribute("currentRole", user.getRole());
        model.addAttribute("roles", List.of("admin", "staff", "user"));
        return "admin/users/editUser";
    }

    @Autowired
    private UsersRepository usersRepository;

/**
 * Handles user update after edit form submission.
 */
@PostMapping("/users/edit/{id}")
public String updateUser(@PathVariable Long id,
                         @ModelAttribute("registerModel") RegisterModel model,
                         @RequestParam String role,
                         BindingResult bindingResult,
                         Model springModel) {

    // Only reject password if it is not empty and fails validation
    if (!model.getPassword().isEmpty()) {
        // Validate password manually if needed
        if (model.getPassword().length() < 8) {
            bindingResult.rejectValue("password", "error.password", "Password must be at least 8 characters");
        }
    }

    if (bindingResult.hasErrors()) {
        springModel.addAttribute("registerModel", model);
        return "admin/users/editUser"; // Ensure this matches the path to the view
    }

    // Fetch existing user from DB
    Optional<UserEntity> existingUserOpt = usersRepository.findById(id);
    if (existingUserOpt.isEmpty()) {
        return "redirect:/admin/users"; // or return an error page
    }

    UserEntity existingUser = existingUserOpt.get();

    // Update fields
    existingUser.setUsername(model.getUsername());
    existingUser.setFirstName(model.getFirstName());
    existingUser.setLastName(model.getLastName());
    existingUser.setRole(role);

    // Only update password if provided and not empty
    if (!model.getPassword().isEmpty()) {
        // Hash the password before saving
        existingUser.setPassword(passwordEncoder.encode(model.getPassword()));
    }

    usersRepository.save(existingUser);

    return "redirect:/admin/users";
}

    
    /**
     * Deletes a user by ID.
     */
    @GetMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        loginService.deleteUser(id);
        return "redirect:/admin/users";
    }

    // -------- Menu Management

    /**
     * Displays the menu management page.
     */
    @GetMapping("/menus")
    public String showMenuManagementPage(Model model) {
        Iterable<MenuEntity> menus = menuService.getAllMenus();
        model.addAttribute("menus", menus);
        return "admin/menus/menuManagement";
    }

    /**
     * Displays the create menu form.
     */
    @GetMapping("/menus/create")
    public String showCreateMenuForm(Model model) {
        model.addAttribute("menu", new HomeModel(null, null, null, null));
        return "admin/menus/createMenu";
    }

    /**
     * Handles menu creation form submission.
     */
    @PostMapping("/menus/create")
    public String createMenu(@ModelAttribute("menu") @Valid HomeModel menu, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "admin/menus/createMenu";
        }

        menuService.createMenu(menu);
        return "redirect:/admin/menus";
    }

    /**
     * Deletes a menu by ID.
     */
    @GetMapping("/menus/delete/{id}")
    public String deleteMenu(@PathVariable("id") Long menuId) {
        menuService.deleteMenu(menuId);
        return "redirect:/admin/menus";
    }

    /**
     * Displays the edit form for a menu.
     */
    @GetMapping("/menus/edit/{id}")
    public String showEditMenuForm(@PathVariable("id") Long id, Model model) {
        MenuEntity menu = menuService.getMenuById(id);
        if (menu != null) {
            model.addAttribute("menu", menu);
            return "admin/menus/editMenu";
        } else {
            return "redirect:/admin/menus";
        }
    }

    /**
     * Handles menu update after form submission.
     */
    @PostMapping("/menus/edit/{id}")
    public String updateMenu(@PathVariable("id") Long id, @ModelAttribute MenuEntity menu) {
        menu.setId(id);
        menuService.updateMenu(menu);
        return "redirect:/admin/menus";
    }
}