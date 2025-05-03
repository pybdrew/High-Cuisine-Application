package com.menu.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

import com.menu.data.entity.ProductEntity;
import com.menu.data.repository.ProductsRepository;
import com.menu.model.ProductModel;

@Service
public class ProductService
{

    private final ProductsRepository productsRepository;

    public ProductService(ProductsRepository productsRepository)
    {
        this.productsRepository = productsRepository;
    }

    public ProductEntity createProduct(ProductModel productModel)
    {
        // Convert ProductModel to ProductEntity ()
        ProductEntity productEntity = new ProductEntity();
        productEntity.setName(productModel.getName());
        productEntity.setDescription(productModel.getDescription());
        productEntity.setType(productModel.getProductType());
        productEntity.setImageUrl(productModel.getImageUrl());

        // Save to database
        return productsRepository.save(productEntity);
    }

    // Get all products and return as List
    public List<ProductEntity> getAllProducts()
    {
        // Convert Iterable to List
        Iterable<ProductEntity> iterable = productsRepository.findAll();

        // Converts to List
        return StreamSupport.stream(iterable.spliterator(), false).collect(Collectors.toList());
    }

    // Method for deletion
    public void deleteProduct(Long productId)
    {
        productsRepository.deleteById(productId);
    }

    public ProductEntity getProductById(Long id)
    {
        // Return null if not found
        return productsRepository.findById(id).orElse(null); 
    }

    public void updateProduct(ProductEntity product)
    {
        // Save updated product
        productsRepository.save(product); 
    }
}
