package com.crealo.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.CorsRegistry;
import org.springframework.web.reactive.config.WebFluxConfigurer;

/**
 * @author Neftaly Lugo
 * @since May 5, 2022
 */
@Configuration
//@EnableWebFlux
public class CORSFilter implements WebFluxConfigurer {

    private static final String FRONTEND_ADMIN_LOCALHOST = "http://localhost:3001";
    private static final String FRONTEND_LOCALHOST = "http://localhost:3000";
    private static final String FRONTEND_STAGING = "https://neftalylugot.github.io";

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                //.allowedOrigins("*")
                .allowedOrigins(FRONTEND_ADMIN_LOCALHOST, FRONTEND_LOCALHOST, FRONTEND_STAGING)
                .allowedMethods("*")
                .allowedHeaders("*");
    }
}
