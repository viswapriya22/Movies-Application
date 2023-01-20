package com.paychex.moviemetadataservicepyx.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource("file:C:\\Users\\sriki\\IdeaProjects\\mongo.env")
public class EnvProperties {
    @Autowired
    private Environment env;
    @Bean
    public String mongoUsername() {
        return env.getProperty("MONGO_USERNAME");
    }

    @Bean
    public String mongoPassword() {
        return env.getProperty("MONGO_PASSWORD");
    }
}