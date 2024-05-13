package com.peakyblinders.peakyblindersfood;

import com.peakyblinders.peakyblindersfood.infrastructure.repository.CustomJpaRepositoryImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = CustomJpaRepositoryImpl.class)
public class PeakyBlindersFoodApiApplication {

	public static void main(String[] args) {

		SpringApplication.run(PeakyBlindersFoodApiApplication.class, args);
	}

}
