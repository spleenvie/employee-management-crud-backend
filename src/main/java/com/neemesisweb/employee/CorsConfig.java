package com.neemesisweb.employee;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class CorsConfig implements WebMvcConfigurer  {
    
    @Override
    public void addCorsMappings(CorsRegistry registry){
        registry.addMapping("/**")
            .allowedOrigins("*") // Autoriser l'origine souhaitée
            .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE") // Méthodes HTTP autorisées
            .allowedHeaders("*"); // Toutes les en-têtes autorisées
    }
}
