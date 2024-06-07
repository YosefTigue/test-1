package dev.yosef.TaskManagementSystem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) ->
                        requests
                                .requestMatchers("/api/auth/**").permitAll() // Allow public access to authentication endpoints
                                .anyRequest().authenticated() // Require authentication for other endpoints
                )
                .formLogin((form) ->
                        form
                                .loginPage("/login")
                                .permitAll()
                )
                .logout((logout) ->
                        logout
                                .permitAll());

        return http.build();
    }
}
