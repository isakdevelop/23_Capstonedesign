package com.repair.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class RepairApplication {
	public static void main(String[] args) {
		SpringApplication.run(RepairApplication.class, args);
	}
}
