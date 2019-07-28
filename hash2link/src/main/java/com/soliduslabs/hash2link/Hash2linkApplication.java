package com.soliduslabs.hash2link;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class Hash2linkApplication {

	public static void main(String[] args) {
		SpringApplication.run(Hash2linkApplication.class, args);
	}

}
