package com.launchcode.parks_for_tails.config;

import com.launchcode.parks_for_tails.models.SpringFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class SpringSecurityConfig implements WebMvcConfigurer {
    @Bean
    public SpringFilter springFilter(){
        return new SpringFilter();
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(springFilter());
    }
}