package com.menu.data.access;

public interface UsersDataAccessInterface<T>
{
    T findByUsername(String username);
}
