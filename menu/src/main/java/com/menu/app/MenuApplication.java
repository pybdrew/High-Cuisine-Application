package com.menu.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;

/**
 * Main entry point for the High Cruise Menu Application.
 * 
 * This class bootstraps the Spring Boot application, sets up component scanning,
 * and enables JDBC repositories for data access.
 */
@SpringBootApplication
@ComponentScan({"com.menu"})
@EnableJdbcRepositories("com.menu.data.repository")
public class MenuApplication
{

    /**
     * Starts the Spring Boot application.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args)
    {
        SpringApplication.run(MenuApplication.class, args);
    }

}