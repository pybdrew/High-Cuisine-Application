package com.menu.data.repository;

import java.util.*;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.menu.data.entity.ProductEntity;

// Repository interface for performing CRUD operations
@Repository
public interface ProductsRepository extends CrudRepository<ProductEntity, Long>
{
    List<ProductEntity> findByType(String type);  

    // Custom query to find product by name
    ProductEntity findByName(String name);
}
