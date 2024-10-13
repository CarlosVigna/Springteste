package com.example.springteste.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3000") // Especifique os domínios permitidos
                .allowedMethods("GET", "POST", "PATCH", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("Requestor-Type", "Authorization", "Content-Type") // Adicione cabeçalhos necessários
                .exposedHeaders("X-Get-Header") // Cabeçalho exposto
                .maxAge(3600); // Tempo máximo para cache
    }
}