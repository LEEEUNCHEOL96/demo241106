package com.example.demo241106;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class Demo241106Application {

	public static void main(String[] args) {
		SpringApplication.run(Demo241106Application.class, args);
	}

}
