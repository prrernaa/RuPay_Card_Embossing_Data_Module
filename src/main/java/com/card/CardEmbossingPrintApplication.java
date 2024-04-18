package com.card;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class CardEmbossingPrintApplication implements CommandLineRunner {
	
	@Autowired

	public static void main(String[] args) {
		SpringApplication.run(CardEmbossingPrintApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
	}
	 
}
