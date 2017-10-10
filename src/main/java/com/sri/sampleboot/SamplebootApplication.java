package com.sri.sampleboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories("com.sri.sampleboot.persistence.repository")
@EntityScan("com.sri.sampleboot.persistence.model")
@SpringBootApplication
public class SamplebootApplication {

	public static void main(String[] args) {
		SpringApplication.run(SamplebootApplication.class, args);
	}
}
