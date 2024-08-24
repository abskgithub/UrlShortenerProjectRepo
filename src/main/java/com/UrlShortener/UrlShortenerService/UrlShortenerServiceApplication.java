package com.UrlShortener.UrlShortenerService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UrlShortenerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UrlShortenerServiceApplication.class, args);
		System.out.println("Url Shortener Service Application");
	}

}
