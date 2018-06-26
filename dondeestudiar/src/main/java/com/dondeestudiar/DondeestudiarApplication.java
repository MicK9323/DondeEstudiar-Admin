package com.dondeestudiar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.dondeestudiar")
@SpringBootApplication
public class DondeestudiarApplication {

	public static void main(String[] args) {
		SpringApplication.run(DondeestudiarApplication.class, args);
	}
	
}
