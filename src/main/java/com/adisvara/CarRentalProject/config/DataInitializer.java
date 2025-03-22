package com.adisvara.CarRentalProject.config;

import com.adisvara.CarRentalProject.model.Car;
import com.adisvara.CarRentalProject.repository.CarRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner initDatabase(CarRepository carRepository) {
        return args -> {
            // Check if we need to initialize (only if repository is empty)
            if (carRepository.count() == 0) {
                // Create a list of cars
                List<Car> cars = Arrays.asList(
                    new Car("Toyota", "Camry", 2023, 45.0),
                    new Car("Honda", "Accord", 2022, 42.0),
                    new Car("BMW", "X5", 2023, 85.0),
                    new Car("Mercedes", "C-Class", 2022, 75.0),
                    new Car("Audi", "A4", 2023, 70.0),
                    new Car("Tesla", "Model 3", 2023, 90.0),
                    new Car("Ford", "Mustang", 2022, 65.0),
                    new Car("Chevrolet", "Camaro", 2023, 68.0),
                    new Car("Nissan", "Altima", 2022, 40.0),
                    new Car("Hyundai", "Sonata", 2023, 38.0)
                );
                
                // Save all cars to the database
                carRepository.saveAll(cars);
                
                System.out.println("Database initialized with " + cars.size() + " cars.");
            }
        };
    }
} 