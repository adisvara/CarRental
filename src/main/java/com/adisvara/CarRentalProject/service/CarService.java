package com.adisvara.CarRentalProject.service;

import com.adisvara.CarRentalProject.model.Car;

import java.util.List;
import java.util.Optional;

public interface CarService {
    List<Car> getAllCars();
    Optional<Car> getCarById(Long id);
    Car addCar(Car car);
    Car updateCar(Long id,Car car);
    void deleteCar(Long id);
}
