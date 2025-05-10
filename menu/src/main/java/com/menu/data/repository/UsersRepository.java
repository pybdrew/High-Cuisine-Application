package com.menu.data.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.menu.data.entity.UserEntity;

/**
 * Repository interface for performing CRUD operations on {@link UserEntity}.
 * 
 * Extends {@link CrudRepository} to support basic data access methods,
 * and includes custom query methods for user-specific operations.
 */
@Repository
public interface UsersRepository extends CrudRepository<UserEntity, Long>
{
    /**
     * Finds a user by their username.
     *
     * @param username the username to search for
     * @return the matching {@link UserEntity}, or null if not found
     */
    UserEntity findByUsername(String username);

    /**
     * Counts the total number of users in the database.
     *
     * @return the total user count
     */
    long count(); 
}