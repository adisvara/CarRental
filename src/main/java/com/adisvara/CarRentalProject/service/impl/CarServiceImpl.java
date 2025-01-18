package com.adisvara.CarRentalProject.service.impl;

import com.adisvara.CarRentalProject.model.Car;
import com.adisvara.CarRentalProject.repository.CarRepository;
import com.adisvara.CarRentalProject.service.CarService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;

    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    @Override
    public Optional<Car> getCarById(Long id) {
        return carRepository.findById(id);
    }

    @Override
    public Car addCar(Car car) {
        return carRepository.save(car);
    }

    @Override
    public Car updateCar(Long id, Car car) {
        Optional<Car> existingCar = carRepository.findById(id);

        if(existingCar.isPresent()){
            Car updatedCar = existingCar.get();
            updatedCar.setMake(car.getMake());
            updatedCar.setModel(car.getModel());
            updatedCar.setYear(car.getYear());
            updatedCar.setPricePerDay(car.getPricePerDay());

            return carRepository.save(updatedCar);
        }

        return null;
    }

    @Override
    public void deleteCar(Long id) {
        carRepository.deleteById(id);
    }
}
