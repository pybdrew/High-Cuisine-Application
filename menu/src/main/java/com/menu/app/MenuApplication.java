package com.menu.app;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;

@SpringBootApplication
@ComponentScan({"com.menu"})
@EnableJdbcRepositories("com.menu.data.repository")
public class MenuApplication
{

	public static void main(String[] args)
	{
		SpringApplication.run(MenuApplication.class, args);
	}

}
