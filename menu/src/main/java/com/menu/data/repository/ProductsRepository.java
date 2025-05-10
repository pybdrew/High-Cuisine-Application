package com.menu.data.repository;

import java.util.*;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.menu.data.entity.ProductEntity;

/**
 * Repository interface for performing CRUD operations on {@link ProductEntity}.
 * 
 * Extends {@link CrudRepository} to provide standard methods like save, findById, findAll, and deleteById.
 * Includes custom query methods for filtering by type and name.
 */
@Repository
public interface ProductsRepository extends CrudRepository<ProductEntity, Long>
{
    /**
     * Finds all products that match the given type (e.g., "Drink", "Sandwich").
     *
     * @param type the type of product to filter by
     * @return a list of matching {@link ProductEntity} objects
     */
    List<ProductEntity> findByType(String type);  

    /**
     * Finds a single product by its name.
     *
     * @param name the name of the product to search for
     * @return the matching {@link ProductEntity}, or null if not found
     */
    ProductEntity findByName(String name);
}