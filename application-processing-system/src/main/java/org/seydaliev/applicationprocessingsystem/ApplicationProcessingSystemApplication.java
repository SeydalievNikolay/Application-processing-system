package org.seydaliev.applicationprocessingsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"org.seydaliev.applicationprocessingsystem"})
public class ApplicationProcessingSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApplicationProcessingSystemApplication.class, args);
	}

}
