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

/**
 * Configures Spring Security for the High Cruise application.
 * 
 * This class sets up access rules, login and logout behavior,
 * and redirects users after successful authentication based on roles.
 */
@Configuration
public class SecurityConfig
{

    /**
     * Defines the security filter chain for HTTP requests.
     *
     * @param http the {@link HttpSecurity} object used to configure security
     * @return the configured {@link SecurityFilterChain}
     * @throws Exception if a security configuration error occurs
     */
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
                .failureUrl("/login?error=notregistered")
                .permitAll()
                .successHandler(authenticationSuccessHandler())
            .and()
            .logout()
                .logoutSuccessUrl("/login")
                .permitAll();

        return http.build();
    }

    /**
     * Custom success handler to redirect users based on their roles after login.
     *
     * @return an {@link AuthenticationSuccessHandler} implementation
     */
    private AuthenticationSuccessHandler authenticationSuccessHandler()
    {
        return new AuthenticationSuccessHandler()
        {
            /**
             * Handles successful authentication by redirecting users based on roles.
             *
             * @param request the HTTP request
             * @param response the HTTP response
             * @param authentication the authentication object containing user details
             * @throws IOException if redirection fails
             */
            @Override
            public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException
            {
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