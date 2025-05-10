package com.menu.app;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Collection;

/**
 * Custom handler for post-login success in Spring Security.
 * 
 * Redirects users based on their roles after successful authentication.
 */
@Component
public class CustomAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler
{

    /**
     * Called when a user is successfully authenticated.
     * Redirects based on user roles.
     *
     * @param request the HTTP request
     * @param response the HTTP response
     * @param authentication the authentication object containing user details
     * @throws IOException if an input or output error occurs
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException 
    {
        String targetUrl = determineTargetUrl(authentication);
        if (response.isCommitted())
        {
            return;
        }
        getRedirectStrategy().sendRedirect(request, response, targetUrl);
    }

    /**
     * Determines the URL to redirect to after successful login.
     * If the user has ROLE_admin, they are redirected to /admin.
     * Otherwise, they are redirected to the homepage (/).
     *
     * @param authentication the authentication object
     * @return the target URL as a string
     */
    protected String determineTargetUrl(Authentication authentication)
    {
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        for (GrantedAuthority grantedAuthority : authorities)
        {
            if ("ROLE_admin".equals(grantedAuthority.getAuthority()))
            {
                return "/admin";
            }
        }
        return "/";
    }
}