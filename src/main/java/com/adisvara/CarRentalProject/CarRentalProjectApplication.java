package com.adisvara.CarRentalProject;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CarRentalProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarRentalProjectApplication.class, args);
		Dotenv dotenv = Dotenv.load();

		System.out.println(dotenv.get("DB_HOST"));
	}

}
