package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.security.JwtAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration configuration) throws Exception {

        return configuration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http) throws Exception {

        http
            // REST API - CSRF disabled
            .csrf(csrf -> csrf.disable())

            // JWT - Stateless authentication
            .sessionManagement(session ->
                session.sessionCreationPolicy(
                    SessionCreationPolicy.STATELESS
                )
            )

            .authorizeHttpRequests(auth -> auth

                // Public endpoints
                .requestMatchers(
                    "/api/auth/**",
                    "/swagger-ui/**",
                    "/swagger-ui.html",
                    "/v3/api-docs/**",
                    "/actuator/**"
                ).permitAll()

                // Student - GET: ADMIN + USER
                .requestMatchers(
                    HttpMethod.GET,
                    "/api/students/**"
                ).hasAnyRole("ADMIN", "USER")

                // Student - Write operations: ADMIN only
                .requestMatchers(
                    HttpMethod.POST,
                    "/api/students/**"
                ).hasRole("ADMIN")

                .requestMatchers(
                    HttpMethod.PUT,
                    "/api/students/**"
                ).hasRole("ADMIN")

                .requestMatchers(
                    HttpMethod.DELETE,
                    "/api/students/**"
                ).hasRole("ADMIN")

                // Other modules - GET: ADMIN + USER
                .requestMatchers(
                    HttpMethod.GET,
                    "/api/teachers/**",
                    "/api/courses/**",
                    "/api/enrollments/**",
                    "/api/exams/**",
                    "/api/grades/**"
                ).hasAnyRole("ADMIN", "USER")

                // Other modules - POST: ADMIN only
                .requestMatchers(
                    HttpMethod.POST,
                    "/api/teachers/**",
                    "/api/courses/**",
                    "/api/enrollments/**",
                    "/api/exams/**",
                    "/api/grades/**"
                ).hasRole("ADMIN")

                // Other modules - PUT: ADMIN only
                .requestMatchers(
                    HttpMethod.PUT,
                    "/api/teachers/**",
                    "/api/courses/**",
                    "/api/enrollments/**",
                    "/api/exams/**",
                    "/api/grades/**"
                ).hasRole("ADMIN")

                // Other modules - DELETE: ADMIN only
                .requestMatchers(
                    HttpMethod.DELETE,
                    "/api/teachers/**",
                    "/api/courses/**",
                    "/api/enrollments/**",
                    "/api/exams/**",
                    "/api/grades/**"
                ).hasRole("ADMIN")

                // All remaining requests require authentication
                .anyRequest().authenticated()
            )

            // JWT filter
            .addFilterBefore(
                jwtAuthenticationFilter,
                UsernamePasswordAuthenticationFilter.class
            );

        return http.build();
    }
}