package com.aleksei.rentcarapi.service;

import com.aleksei.rentcarapi.entity.Car;
import com.aleksei.rentcarapi.repository.CarRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * CarService is a service class that provides methods for managing cars in the system.
 * It uses CarRepository to perform database operations.
 */
@Service
@Transactional(readOnly = true)
public class CarService {
    private final CarRepository carRepository;

    /**
     * Constructs a new CarService with the given CarRepository.
     *
     * @param repository the CarRepository to use for database operations
     */
    @Autowired
    public CarService(CarRepository repository) {
        this.carRepository = repository;
    }

    /**
     * Retrieves all cars from the database.
     *
     * @return a list of all cars
     */
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    /**
     * Retrieves a car by its ID.
     *
     * @param id the ID of the car to retrieve
     * @return the car with the given ID, or null if no such car exists
     */
    public Car getCar(Long id) {
        var car = carRepository.findById(id);
        return car.orElse(null);
    }

    /**
     * Creates a new car in the database.
     *
     * @param car the car to create
     */
    @Transactional
    public void create(Car car) {
        carRepository.save(car);
    }

    /**
     * Deletes a car by its ID.
     *
     * @param id the ID of the car to delete
     */
    @Transactional
    public void deleteCar(Long id) {
        carRepository.deleteById(id);
    }

    /**
     * Updates a car in the database.
     *
     * @param id the ID of the car to update
     * @param updatedCar the car with updated information
     * @throws EntityNotFoundException if no car with the given ID exists
     */
    @Transactional
    public void update(Long id, Car updatedCar) {
        var existingCar = carRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Car with id " + id + " not found"));
        if (!existingCar.getBrand().equals(updatedCar.getBrand())) {
            existingCar.setBrand(updatedCar.getBrand());
        }
        if (existingCar.getModel().equals(updatedCar.getModel())) {
            existingCar.setModel(updatedCar.getModel());
        }
        if (existingCar.getYearOfCreating() == updatedCar.getYearOfCreating()) {
            existingCar.setYearOfCreating(updatedCar.getYearOfCreating());
        }
        existingCar.setColor(updatedCar.getColor());
        existingCar.setKilometers(updatedCar.getKilometers());
        carRepository.save(existingCar);
    }
}
