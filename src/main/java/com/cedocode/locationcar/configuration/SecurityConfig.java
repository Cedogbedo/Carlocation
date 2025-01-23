package com.cedocode.locationcar.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authorizeRequests -> 
                authorizeRequests
                    .requestMatchers("/", "/index.html", "/registration", "/login").permitAll()  // Allow public access to these URLs
                    .requestMatchers("/admin/**").hasRole("ADMIN") // For admin-specific pages
                    .requestMatchers("/client/**").hasRole("USER") // for user specific
                    .anyRequest().authenticated()
            )
            .formLogin(formLogin -> 
                formLogin
                    .loginPage("/login").permitAll() // Custom login page
                    .successHandler(new CustomAuthenticationSuccessHandler()) // Use custom success handler
            )
            .logout(logout -> 
                logout
                    .logoutUrl("/logout") // Set the logout URL
                    .logoutSuccessUrl("/index.html") // Redirect to the homepage after logout
                    .permitAll() // Allow everyone to log out
            );
        return http.build();
    }

    @Bean
    public CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler() {
        return new CustomAuthenticationSuccessHandler();
    }
}
