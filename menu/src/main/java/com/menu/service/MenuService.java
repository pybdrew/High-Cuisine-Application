package com.menu.service;

import com.menu.data.entity.MenuEntity;
import com.menu.data.repository.MenuRepository;
import com.menu.model.HomeModel;

import jakarta.validation.Valid;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MenuService {

    private final MenuRepository menuRepository;

    public MenuService(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    // Get all menus
    public Iterable<MenuEntity> getAllMenus() {
        return menuRepository.findAll();
    }

    // Create Menu entity
    public MenuEntity createMenu(HomeModel homeModel) {
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
    public MenuEntity getMenuById(Long id) {
        Optional<MenuEntity> menu = menuRepository.findById(id);
        return menu.orElse(null); // Return the menu or null if not found
    }

    // Update an existing menu
    public void updateMenu(MenuEntity menuEntity) {
        menuRepository.save(menuEntity); // Save the updated menu
    }

    // Delete a menu by ID
    public void deleteMenu(Long id) {
        menuRepository.deleteById(id); // Delete the menu from the database
    }
}
