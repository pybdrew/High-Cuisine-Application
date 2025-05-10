package com.menu.data.repository;

import com.menu.data.entity.MenuEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for performing CRUD operations on {@link MenuEntity}.
 * 
 * Extends {@link CrudRepository} to provide methods such as save, findById, findAll, and deleteById.
 */
@Repository
public interface MenuRepository extends CrudRepository<MenuEntity, Long>
{
    // Additional query methods can be defined here if needed.
}
