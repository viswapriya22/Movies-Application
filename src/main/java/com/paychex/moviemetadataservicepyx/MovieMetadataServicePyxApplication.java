package com.paychex.moviemetadataservicepyx;

import com.paychex.moviemetadataservicepyx.config.EnvProperties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@ComponentScan
@Import(EnvProperties.class)
public class MovieMetadataServicePyxApplication {
	@Value("${MONGO_USERNAME}")
	private String mongoUsername;
	@Value("${MONGO_PASSWORD}")
	private String mongoPassword;
	public static void main(String[] args) {
		SpringApplication.run(MovieMetadataServicePyxApplication.class, args);
	}

}
