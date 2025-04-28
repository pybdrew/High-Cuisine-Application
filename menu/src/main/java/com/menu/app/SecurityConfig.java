package com.menu.app;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@Configuration
public class SecurityConfig
{

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
    {
        http
            .csrf().disable()
            .authorizeHttpRequests()
                .requestMatchers("/login", "/register", "/images/**", "/css/**").permitAll()
                .anyRequest().authenticated()
            .and()
            .formLogin()
                .loginPage("/login")
                .permitAll()
                .successHandler(authenticationSuccessHandler())
            .and()
            .logout()
                .permitAll();

        return http.build();
    }

    private AuthenticationSuccessHandler authenticationSuccessHandler()
    {
        return new AuthenticationSuccessHandler()
        {
            @Override
            public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException
            {
                // Check if the authenticated user has the 'admin' role
                if (authentication.getAuthorities().stream().anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_admin")))
                {
                    response.sendRedirect("/admin");  
                }
                else
                {
                    response.sendRedirect("/");
                }
            }
        };
    }
}
