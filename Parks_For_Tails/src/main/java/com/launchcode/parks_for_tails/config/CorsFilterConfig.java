package com.launchcode.parks_for_tails.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import java.util.List;

/**
 * Configuration class for CORS filtering.
 * allows servers to specify who can access their resources from the browser, helping prevent
 * unauthorized access to resources and enhancing security.
 */
@Configuration
public class CorsFilterConfig {

    // Injecting properties from application.yml
    @Value("${cors.allowed-origins}")
    private String allowedOrigins;

    @Value("${cors.allowed-methods}")
    private String allowedMethods;

    @Value("${cors.allowed-headers}")
    private String allowedHeaders;

    @Value("${cors.exposed-headers}")
    private String exposedHeaders;

    @Value("${cors.allow-credentials}")
    private boolean allowCredentials;

    /**
     * Bean method to configure CORS filtering.
     *
     * @return CorsFilter configured with the specified properties
     */
    @Bean
    public CorsFilter corsFilter() {
        // Create a new CorsConfiguration instance
        CorsConfiguration corsConfiguration = new CorsConfiguration();

        // Set allowed origins, methods, headers, exposed headers, and credentials
        corsConfiguration.setAllowedOrigins(List.of(allowedOrigins.split(",")));
        corsConfiguration.setAllowedMethods(List.of(allowedMethods.split(",")));
        corsConfiguration.setAllowedHeaders(List.of(allowedHeaders.split(",")));
        corsConfiguration.setExposedHeaders(List.of(exposedHeaders.split(",")));
        corsConfiguration.setAllowCredentials(allowCredentials);

        // Create a new UrlBasedCorsConfigurationSource instance
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        // Register CORS configuration for all paths
        source.registerCorsConfiguration("/**", corsConfiguration);

        // Return a new CorsFilter instance with the configured source
        return new CorsFilter(source);
    }
}
