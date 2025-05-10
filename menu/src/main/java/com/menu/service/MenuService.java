package com.menu.service;

import com.menu.data.entity.MenuEntity;
import com.menu.data.repository.MenuRepository;
import com.menu.model.HomeModel;

import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service class for managing menu-related operations.
 * 
 * Provides business logic for retrieving, creating, updating, and deleting menu items.
 */
@Service
public class MenuService
{
    private final MenuRepository menuRepository;

    /**
     * Constructs a {@code MenuService} with repository injection.
     *
     * @param menuRepository the repository used to access menu data
     */
    public MenuService(MenuRepository menuRepository)
    {
        this.menuRepository = menuRepository;
    }

    /**
     * Retrieves all menus from the database.
     *
     * @return an iterable list of {@link MenuEntity} objects
     */
    public Iterable<MenuEntity> getAllMenus()
    {
        return menuRepository.findAll();
    }

    /**
     * Creates a new menu entry using a {@link HomeModel} as input.
     * Converts it into a {@link MenuEntity} before saving.
     *
     * @param homeModel the form model containing menu data
     * @return the saved {@link MenuEntity}
     */
    public MenuEntity createMenu(HomeModel homeModel)
    {
        MenuEntity menuEntity = new MenuEntity();
        menuEntity.setName(homeModel.getName());
        menuEntity.setDescription(homeModel.getDescription());
        menuEntity.setType(homeModel.getProductType());
        menuEntity.setImageUrl(homeModel.getImageUrl());

        return menuRepository.save(menuEntity);
    }

    /**
     * Retrieves a menu by its unique ID.
     *
     * @param id the ID of the menu to retrieve
     * @return the {@link MenuEntity} or null if not found
     */
    public MenuEntity getMenuById(Long id)
    {
        Optional<MenuEntity> menu = menuRepository.findById(id);
        return menu.orElse(null);
    }

    /**
     * Updates an existing menu in the database.
     *
     * @param menuEntity the updated menu entity to save
     */
    public void updateMenu(MenuEntity menuEntity)
    {
        menuRepository.save(menuEntity);
    }

    /**
     * Deletes a menu from the database using its ID.
     *
     * @param id the ID of the menu to delete
     */
    public void deleteMenu(Long id)
    {
        menuRepository.deleteById(id);
    }
}