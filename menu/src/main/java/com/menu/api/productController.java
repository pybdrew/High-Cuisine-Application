package com.menu.api;

import com.menu.data.entity.ProductEntity;
import com.menu.data.repository.ProductsRepository;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for managing product-related API requests.
 */
@RestController
@RequestMapping("/api/products")
public class productController
{ 
    private final ProductsRepository productsRepository;

    /**
     * Constructor with dependency injection for ProductsRepository.
     *
     * @param productsRepository the repository used to access product data
     */
    public productController(ProductsRepository productsRepository)
    {
        this.productsRepository = productsRepository;
    }

    /**
     * Retrieves all products from the database.
     *
     * @return an iterable list of ProductEntity objects
     */
    @GetMapping
    public Iterable<ProductEntity> getAllProducts()
    {
        return productsRepository.findAll();
    }

    /**
     * Retrieves a specific product by its name.
     *
     * @param name the name of the product to retrieve
     * @return the matching ProductEntity, or null if not found
     */
    @GetMapping("/{name}")
    public ProductEntity getProductByName(@PathVariable String name)
    {
        return productsRepository.findByName(name);
    }
}