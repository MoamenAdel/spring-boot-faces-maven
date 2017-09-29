package com.research;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@PropertySource("research.properties")
@EnableJpaRepositories("com")
@SpringBootApplication
public class ResearchCenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(ResearchCenterApplication.class, args);
    }

}
