package com.cedocode.locationcar.configuration;

import java.io.IOException;

import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.core.Authentication;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, 
            Authentication authentication) throws IOException {
        // Check user roles and redirect accordingly
        boolean isAdmin = authentication.getAuthorities().stream()
            .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ADMIN"));

        if (isAdmin) {
            response.sendRedirect("/admin/home"); // Redirect admin to admin home
        } else {
            response.sendRedirect("/client/clienthome"); // Redirect regular users to client home
        }
    }
}