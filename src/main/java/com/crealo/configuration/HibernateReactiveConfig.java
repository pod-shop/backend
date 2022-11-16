package com.crealo.configuration;

import org.hibernate.reactive.mutiny.Mutiny;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.Persistence;
import java.util.Map;

@Configuration
public class HibernateReactiveConfig {

    @Value("${javax.persistence.jdbc.url}")
    private String url;
    @Value("${javax.persistence.jdbc.user}")
    private String user;
    @Value("${javax.persistence.jdbc.password}")
    private String password;
    @Value("${javax.persistence.schema-generation.database.action}")
    private String action;

    @Bean
    public Mutiny.SessionFactory sessionFactory() {
        final var props = Map.of(
                "javax.persistence.jdbc.url", url,
                "javax.persistence.jdbc.user", user,
                "javax.persistence.jdbc.password", password,
                "javax.persistence.schema-generation.database.action", action
        );

        return Persistence.createEntityManagerFactory("crealoPU", props)
                .unwrap(Mutiny.SessionFactory.class);
    }
}
