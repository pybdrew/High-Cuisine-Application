package com.menu.api;

import com.menu.data.entity.ProductEntity;
import com.menu.data.repository.ProductsRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class productController
{ 

    private final ProductsRepository productsRepository;

    // Constructor injection
    public productController(ProductsRepository productsRepository)
    {
        this.productsRepository = productsRepository;
    }

    // Endpoint to get all products
    @GetMapping
    public Iterable<ProductEntity> getAllProducts()
    {
        return productsRepository.findAll();
    }

    // Endpoint to get a specific product by name
    @GetMapping("/{name}")
    public ProductEntity getProductByName(@PathVariable String name)
    {
        return productsRepository.findByName(name);
    }
}
