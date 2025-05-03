package com.menu.service;

import com.menu.data.entity.MenuEntity;
import com.menu.data.repository.MenuRepository;
import com.menu.model.HomeModel;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MenuService
{

    private final MenuRepository menuRepository;

    public MenuService(MenuRepository menuRepository)
    {
        this.menuRepository = menuRepository;
    }

    // Get all menus
    public Iterable<MenuEntity> getAllMenus()
    {
        return menuRepository.findAll();
    }

    // Create Menu entity
    public MenuEntity createMenu(HomeModel homeModel)
    {
        // Convert HomeModel to MenuEntity
        MenuEntity menuEntity = new MenuEntity();
        menuEntity.setName(homeModel.getName());
        menuEntity.setDescription(homeModel.getDescription());
        menuEntity.setType(homeModel.getProductType());
        menuEntity.setImageUrl(homeModel.getImageUrl());
    
        // Save the MenuEntity to the database
        return menuRepository.save(menuEntity);
    }
    

    // Get a menu by ID
    public MenuEntity getMenuById(Long id)
    {
        Optional<MenuEntity> menu = menuRepository.findById(id);
        // Return the menu or null if not found
        return menu.orElse(null);
    }

    // Update an existing menu
    public void updateMenu(MenuEntity menuEntity)
    {
        // Save the updated menu
        menuRepository.save(menuEntity);
    }

    // Delete a menu by ID
    public void deleteMenu(Long id)
    {
        // Delete the menu from the database
        menuRepository.deleteById(id);
    }
}
