package com.example.demo_rest_2.security.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;

import java.time.Instant;

@Configuration
public class SecurityBeansConfig {

    @Bean
    public AuthenticationEntryPoint customAuthenticationEntryPoint(
            ObjectMapper objectMapper
    ) {
        return (request, response, authException) -> {

            RestError restError = new RestError(
                    HttpStatus.UNAUTHORIZED.toString(),
                    Instant.now()
            );

            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);

            objectMapper.writeValue(response.getOutputStream(), restError);
        };
    }

    @Bean
    public AccessDeniedHandler customAccessDeniedHandler(
            ObjectMapper objectMapper
    ) {
        return (request, response, accessDeniedException) -> {
            RestError restError = new RestError(
                    HttpStatus.FORBIDDEN.toString(),
                    Instant.now()
            );

            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            objectMapper.writeValue(response.getOutputStream(), restError);
        };
    }

}

