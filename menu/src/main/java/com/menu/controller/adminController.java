package com.menu.controller;

import org.springframework.http.HttpHeaders;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.menu.data.entity.ProductEntity;
import com.menu.model.ProductModel;
import com.menu.service.ProductService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/product")
public class adminController {

    private final ProductService productService;

    @Autowired
    public adminController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("")
    public String showProductManagementPage(Model model) {
        List<ProductEntity> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "product/productManagement";
    }

    @GetMapping("/image/{id}")
    @ResponseBody
    public ResponseEntity<byte[]> getProductImage(@PathVariable Long id) {
        ProductEntity product = productService.getProductById(id);
        if (product != null && product.getImageData() != null) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);
            return new ResponseEntity<>(product.getImageData(), headers, HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/create")
    public String showCreateProductForm(Model model) {
        model.addAttribute("product", new ProductModel());
        return "product/createProduct";
    }

    @PostMapping("/create")
    public String createProduct(@ModelAttribute("product") @Valid ProductModel product, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "product/createProduct";
        }

        productService.createProduct(product);

        if ("Drink".equalsIgnoreCase(product.getProductType())) {
            return "redirect:/drink";
        } else if ("Sandwich".equalsIgnoreCase(product.getProductType())) {
            return "redirect:/sandwich";
        } else {
            return "redirect:/";
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") Long productId) {
        productService.deleteProduct(productId);
        return "redirect:/product";
    }

    @GetMapping("/edit/{id}")
    public String showEditProductPage(@PathVariable("id") Long id, Model model) {
        ProductEntity product = productService.getProductById(id);
        if (product != null) {
            ProductModel modelForm = new ProductModel();
            modelForm.setId(product.getId());
            modelForm.setName(product.getName());
            modelForm.setDescription(product.getDescription());
            modelForm.setImageUrl(product.getImageUrl());
            modelForm.setProductType(product.getType());
            model.addAttribute("product", modelForm);
            return "product/editProduct";
        } else {
            return "redirect:/product";
        }
    }

    @PostMapping("/edit/{id}")
    public String updateProduct(@PathVariable("id") Long id,
                                @ModelAttribute("product") @Valid ProductModel productModel,
                                BindingResult bindingResult,
                                Model model) {
    
        // Get the existing product to verify if it already has image data or URL
        ProductEntity existingProduct = productService.getProductById(id);
    
        boolean noFileProvided = productModel.getImageFile() == null || productModel.getImageFile().isEmpty();
        boolean noUrlProvided = productModel.getImageUrl() == null || productModel.getImageUrl().isBlank();
        boolean noExistingImage = (existingProduct.getImageData() == null || existingProduct.getImageData().length == 0)
                                  && (existingProduct.getImageUrl() == null || existingProduct.getImageUrl().isBlank());
    
        if (noFileProvided && noUrlProvided && noExistingImage) {
            bindingResult.rejectValue("imageUrl", "error.product", "Please provide an image file or URL.");
        }
    
        if (bindingResult.hasErrors()) {
            model.addAttribute("productId", id);
            return "product/editProduct";
        }
    
        productService.updateProduct(id, productModel);
        return "redirect:/product";
    }
}