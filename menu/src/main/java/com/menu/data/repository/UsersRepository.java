package com.menu.data.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.menu.data.entity.UserEntity;

// Repository interface for performing CRUD operations
@Repository
public interface UsersRepository extends CrudRepository<UserEntity, Long>
{
    // Find by Username
    UserEntity findByUsername(String username);

    // Count total number of Users
    long count(); 
}
