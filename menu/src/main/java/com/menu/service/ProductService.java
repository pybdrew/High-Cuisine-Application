package com.menu.service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.menu.data.entity.ProductEntity;
import com.menu.data.repository.ProductsRepository;
import com.menu.model.ProductModel;

@Service
public class ProductService {

    private final ProductsRepository productsRepository;

    @Autowired
    public ProductService(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    public ProductEntity createProduct(ProductModel productModel) {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setName(productModel.getName());
        productEntity.setDescription(productModel.getDescription());
        productEntity.setType(productModel.getProductType());

        MultipartFile imageFile = productModel.getImageFile();
        try {
            if (imageFile != null && !imageFile.isEmpty()) {
                productEntity.setImageData(imageFile.getBytes());
                productEntity.setImageUrl(null); // Use image data if uploaded
            } else {
                productEntity.setImageUrl(productModel.getImageUrl());
                productEntity.setImageData(null); // Use image URL if no upload
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to process image file", e);
        }

        return productsRepository.save(productEntity);
    }

    public void updateProduct(Long id, ProductModel model) {
        ProductEntity product = getProductById(id);
        if (product == null) return;

        product.setName(model.getName());
        product.setDescription(model.getDescription());
        product.setType(model.getProductType());

        try {
            MultipartFile imageFile = model.getImageFile();
            if (imageFile != null && !imageFile.isEmpty()) {
                product.setImageData(imageFile.getBytes());
                product.setImageUrl(null);
            } else if (model.getImageUrl() != null && !model.getImageUrl().isBlank()) {
                product.setImageUrl(model.getImageUrl());
                product.setImageData(null);
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to process uploaded image", e);
        }

        productsRepository.save(product);
    }

    public List<ProductEntity> getAllProducts() {
        Iterable<ProductEntity> iterable = productsRepository.findAll();
        return StreamSupport.stream(iterable.spliterator(), false).collect(Collectors.toList());
    }

    public void deleteProduct(Long productId) {
        productsRepository.deleteById(productId);
    }

    public ProductEntity getProductById(Long id) {
        return productsRepository.findById(id).orElse(null);
    }

    public void updateProduct(ProductEntity product) {
        productsRepository.save(product);
    }
}