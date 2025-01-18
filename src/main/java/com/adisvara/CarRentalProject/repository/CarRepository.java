package com.adisvara.CarRentalProject.repository;

import com.adisvara.CarRentalProject.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car,Long> {
}
