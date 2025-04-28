package com.menu.data.repository;

import com.menu.data.entity.MenuEntity;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuRepository extends CrudRepository<MenuEntity, Long>
{
    
}
