package com.menu.data.access;

/**
 * Interface for accessing user data by username.
 *
 * @param <T> the type of user object returned (e.g., UserEntity or UserModel)
 */
public interface UsersDataAccessInterface<T>
{
    /**
     * Finds a user by their username.
     *
     * @param username the username to search for
     * @return the user object associated with the given username, or null if not found
     */
    T findByUsername(String username);
}