package com.menu.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.menu.data.entity.*;
import com.menu.model.*;
import com.menu.service.*;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/admin")
public class adminController {

    private final ProductService productService;
    private final LoginService loginService;
    private final MenuService menuService;

    public adminController(ProductService productService, LoginService loginService, MenuService menuService) {
        this.productService = productService;
        this.loginService = loginService;
        this.menuService = menuService;
    }

    @GetMapping("")
    public String homePage(Model model) {
        return "admin/home";
    }

    // -------- Product Management

    @GetMapping("/product")
    public String showProductManagementPage(Model model) {
        List<ProductEntity> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "/admin/product/productManagement";
    }

    @GetMapping("/product/create")
    public String showCreateProductForm(Model model) {
        model.addAttribute("product", new ProductModel());
        return "admin/product/createProduct";
    }

    @PostMapping("/product/create")
    public String createProduct(@ModelAttribute("product") @Valid ProductModel product,
                                BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "admin/product/create";
        }

        productService.createProduct(product);
        System.out.println("Product created: " + product);

        if ("Drink".equalsIgnoreCase(product.getProductType())) {
            return "redirect:/drink";
        } else if ("Sandwich".equalsIgnoreCase(product.getProductType())) {
            return "redirect:/sandwich";
        } else {
            return "redirect:/";
        }
    }

    @GetMapping("/product/delete/{id}")
    public String deleteProduct(@PathVariable("id") Long productId) {
        productService.deleteProduct(productId);
        return "redirect:/admin";
    }

    @GetMapping("/product/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        ProductEntity entity = productService.getProductById(id);
        ProductModel formModel = new ProductModel(
            entity.getId(),
            entity.getName(),
            entity.getDescription(),
            entity.getImageUrl(),
            entity.getType()
        );
        model.addAttribute("product", formModel); // ✅ CORRECT BINDING NAME
        return "admin/product/editProduct";
    }

    @PostMapping("/product/edit/{id}")
    public String updateProduct(@PathVariable("id") Long id,
                                @ModelAttribute("product") @Valid ProductModel productModel,
                                BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "admin/product/editProduct";
        }

        productService.updateProduct(id, productModel);
        return "redirect:/admin/product";
    }

    @GetMapping(value = "/product/image/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
@ResponseBody
public ResponseEntity<byte[]> serveProductImage(@PathVariable Long id) {
    ProductEntity product = productService.getProductById(id);
    if (product != null && product.getImageData() != null) {
        return ResponseEntity.ok().body(product.getImageData());
    } else {
        return ResponseEntity.notFound().build();
    }
}

    // -------- User Management

    @GetMapping("/users")
    public String showUserManagement(Model model) {
        List<UserEntity> users = loginService.getAllUsers();
        model.addAttribute("users", users);
        return "admin/users/userManagement";
    }

    @GetMapping("/users/create")
    public String showCreateUserPage(Model model) {
        model.addAttribute("registerModel", new RegisterModel());
        model.addAttribute("roles", List.of("admin", "staff", "customer"));
        return "admin/users/createUser";
    }

    @PostMapping("/user/create")
    public String createUser(@ModelAttribute("registerModel") @Valid RegisterModel registerModel,
                             BindingResult bindingResult,
                             @RequestParam("role") String role,
                             Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("roles", List.of("admin", "staff", "customer"));
            return "admin/users/createUser";
        }
        loginService.saveUser(registerModel, role);
        return "redirect:/admin/users";
    }

    @GetMapping("/user/edit/{id}")
    public String showEditUserForm(@PathVariable Long id, Model model) {
        UserEntity user = loginService.getUserById(id);
        if (user == null) {
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
        model.addAttribute("roles", List.of("admin", "staff", "customer"));
        return "admin/users/editUser";
    }

    @PostMapping("/user/edit/{id}")
    public String updateUser(@PathVariable Long id,
                             @ModelAttribute("registerModel") @Valid RegisterModel registerModel,
                             @RequestParam("role") String role,
                             BindingResult bindingResult,
                             Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("roles", List.of("admin", "staff", "customer"));
            return "admin/users/editUser";
        }

        loginService.updateUser(id, registerModel, role);
        return "redirect:/admin/users";
    }

    @GetMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        loginService.deleteUser(id);
        return "redirect:/admin/users";
    }

    // -------- Menu Management

    @GetMapping("/menus")
    public String showMenuManagementPage(Model model) {
        Iterable<MenuEntity> menus = menuService.getAllMenus();
        model.addAttribute("menus", menus);
        return "admin/menus/menuManagement";
    }

    @GetMapping("/menus/create")
    public String showCreateMenuForm(Model model) {
        model.addAttribute("menu", new HomeModel(null, null, null, null));
        return "admin/menus/createMenu";
    }

    @PostMapping("/menus/create")
    public String createMenu(@ModelAttribute("menu") @Valid HomeModel menu, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "admin/menus/createMenu";
        }

        menuService.createMenu(menu);
        return "redirect:/admin/menus";
    }

    @GetMapping("/menus/delete/{id}")
    public String deleteMenu(@PathVariable("id") Long menuId) {
        menuService.deleteMenu(menuId);
        return "redirect:/admin/menus";
    }

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

    @PostMapping("/menus/edit/{id}")
    public String updateMenu(@PathVariable("id") Long id, @ModelAttribute MenuEntity menu) {
        menu.setId(id);
        menuService.updateMenu(menu);
        return "redirect:/admin/menus";
    }
}