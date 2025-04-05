package com.menu.app;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.menu"})
public class MenuApplication
{

	public static void main(String[] args)
	{
		SpringApplication.run(MenuApplication.class, args);
	}

}
