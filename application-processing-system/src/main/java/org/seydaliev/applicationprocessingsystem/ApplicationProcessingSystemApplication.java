package org.seydaliev.applicationprocessingsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@EnableMethodSecurity
@SpringBootApplication(scanBasePackages = {"org.seydaliev.applicationprocessingsystem"})
public class ApplicationProcessingSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApplicationProcessingSystemApplication.class, args);
	}

}
