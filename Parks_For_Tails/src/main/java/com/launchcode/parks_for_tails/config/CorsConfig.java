package com.launchcode.parks_for_tails.config;

import org.springframework.context.annotation.Configuration;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// Configuration class for CORS (Cross-Origin Resource Sharing) settings
@Configuration
public class CorsConfig implements WebMvcConfigurer {
    // Configures allowed origins, methods, headers, and credentials for CORS.
    @Override
    // specify which endpoints are allowed to be accessed from different origins.
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
                .allowedOrigins("http://localhost:3000")
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("*")
                .exposedHeaders("Authorization")
                .allowCredentials(true);
    }
}
