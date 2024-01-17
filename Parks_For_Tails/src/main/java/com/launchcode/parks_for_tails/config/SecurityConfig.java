// This class configures the security settings for your application using Spring Security.
// It defines how different parts of your application are secured and who has access to them.

package com.launchcode.parks_for_tails.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Arrays;
import java.util.List;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {

    // This method creates a SecurityFilterChain, which defines security configuration for the application.
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // Configuring URL-based security rules
        http.authorizeHttpRequests(authorizeRequests ->
                authorizeRequests
                        // Allowing public access to certain URLs
                        .requestMatchers("/", "/signin", "/signup", "/registration", "/login").permitAll()
                        // Restricting access to URLs based on user roles
                        .requestMatchers("/users/**", "/apps/**").hasAuthority("ADMIN")
                        .requestMatchers("/myapps/**").hasAuthority("CLIENT")
                        .requestMatchers("/register").permitAll() // Allowing access to /register without authentication
                        .anyRequest().authenticated()
        );

        // Configuring form-based login
        http.formLogin(formLogin -> formLogin
                .loginPage("/landing") // Specifying the login page
                .usernameParameter("email") // Configuring the parameter for the username
                .defaultSuccessUrl("/", true) // Redirecting to the home page after successful login
                .permitAll()
        );

        // Configuring remember-me functionality
        http.rememberMe(withDefaults());

        // Configuring logout functionality
        http.logout(logout -> logout.logoutUrl("/signout").permitAll());

        // Building and returning the configured HttpSecurity
        return http.build();
    }

    // Defining a whitelist of URLs that can be accessed without authentication
    private static final List<String> whitelist = Arrays.asList("/api", "/welcome", "/register", "/login", "/css", "/images");

    // Checking if a given path is whitelisted (accessible without authentication)
    private static boolean isWhitelisted(String path) {
        for (String pathRoot : whitelist) {
            if (path.equals("/") || path.startsWith(pathRoot) || "/login".equals(path)) {
                return true;
            }
        }
        return false;
    }
}
