package com.menu.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

import com.menu.data.entity.ProductEntity;
import com.menu.data.repository.ProductsRepository;
import com.menu.model.ProductModel;

/**
 * Service class for managing products in the system.
 * 
 * Handles business logic for creating, retrieving, updating, and deleting product entries.
 */
@Service
public class ProductService
{
    private final ProductsRepository productsRepository;

    /**
     * Constructs a {@code ProductService} with the given repository.
     *
     * @param productsRepository the repository used for product data access
     */
    public ProductService(ProductsRepository productsRepository)
    {
        this.productsRepository = productsRepository;
    }

    /**
     * Creates a new product using data from the provided model.
     *
     * @param productModel the form model containing product data
     * @return the saved {@link ProductEntity}
     */
    public ProductEntity createProduct(ProductModel productModel)
    {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setName(productModel.getName());
        productEntity.setDescription(productModel.getDescription());
        productEntity.setType(productModel.getProductType());
        productEntity.setImageUrl(productModel.getImageUrl());

        return productsRepository.save(productEntity);
    }

    /**
     * Retrieves all products from the database as a list.
     *
     * @return a list of {@link ProductEntity} objects
     */
    public List<ProductEntity> getAllProducts()
    {
        Iterable<ProductEntity> iterable = productsRepository.findAll();
        return StreamSupport.stream(iterable.spliterator(), false).collect(Collectors.toList());
    }

    /**
     * Deletes a product by its ID.
     *
     * @param productId the ID of the product to delete
     */
    public void deleteProduct(Long productId)
    {
        productsRepository.deleteById(productId);
    }

    /**
     * Retrieves a product by its ID.
     *
     * @param id the ID of the product to retrieve
     * @return the {@link ProductEntity}, or null if not found
     */
    public ProductEntity getProductById(Long id)
    {
        return productsRepository.findById(id).orElse(null); 
    }

    /**
     * Updates an existing product's details based on the provided model.
     *
     * Updates name, description, type, and optionally image (file or URL).
     *
     * @param id the ID of the product to update
     * @param productModel the form model with updated product data
     */
    public void updateProduct(Long id, ProductModel productModel) {
        ProductEntity existingProduct = getProductById(id);
        if (existingProduct == null) {
            return; // Optionally throw exception
        }

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
        } catch (Exception e) {
            e.printStackTrace();
        }

        productsRepository.save(existingProduct);
    }
}