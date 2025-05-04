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

    public void updateProduct(Long id, ProductModel productModel) {
        ProductEntity existingProduct = getProductById(id);
        if (existingProduct == null) {
            return; // or throw exception
        }
    
        // Update name, description, and type
        existingProduct.setName(productModel.getName());
        existingProduct.setDescription(productModel.getDescription());
        existingProduct.setType(productModel.getProductType());
    
        try {
            boolean hasNewFile = productModel.getImageFile() != null && !productModel.getImageFile().isEmpty();
            boolean hasImageUrl = productModel.getImageUrl() != null && !productModel.getImageUrl().isBlank();
    
            if (hasNewFile) {
                // Save uploaded file and clear URL
                existingProduct.setImageData(productModel.getImageFile().getBytes());
                existingProduct.setImageUrl(null);
            } else if (hasImageUrl) {
                // Save URL and clear file
                existingProduct.setImageUrl(productModel.getImageUrl());
                existingProduct.setImageData(null);
            }
    
            // If neither provided, do not update image fields
        } catch (Exception e) {
            e.printStackTrace();
        }
    
        productsRepository.save(existingProduct);
    }
}
