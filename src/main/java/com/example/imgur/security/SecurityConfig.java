package com.example.imgur.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig  {

	@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable() // Disable CSRF for simplicity (you may want to configure it properly)
            .authorizeRequests()
                .requestMatchers("/api/users/register", "/api/users/login").permitAll() // Allow access to register/login endpoints
                .anyRequest().authenticated() // Require authentication for all other requests
            .and()
            .httpBasic(); // Simple HTTP Basic Authentication (You can also configure OAuth2 here)
        
        return http.build();
    }
}
