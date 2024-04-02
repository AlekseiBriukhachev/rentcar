package com.aleksei.rentcarapi.controllers;

import com.aleksei.rentcarapi.entity.Car;
import com.aleksei.rentcarapi.service.CarService;
import com.aleksei.rentcarapi.service.RentalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/**
 * CarController is a REST controller that provides endpoints for managing cars in the system.
 * It uses CarService and RentalService to perform business logic operations.
 */
@RestController
@RequestMapping("/api/car")
@Tag(name = "Car Controller", description = "Car API")
public class CarController {
    private final CarService carService;
    private final RentalService rentalService;

    /**
     * Constructs a new CarController with the given CarService and RentalService.
     *
     * @param carService the CarService to use for car-related operations
     * @param rentalService the RentalService to use for rental-related operations
     */
    @Autowired
    public CarController(CarService carService, RentalService rentalService) {
        this.carService = carService;
        this.rentalService = rentalService;
    }

    /**
     * Endpoint to get a car by its ID.
     *
     * @param id the ID of the car to retrieve
     * @return a ResponseEntity containing the car and an HTTP status code
     */
    @GetMapping("/{id}")
    @Operation(summary = "Get car by ID", description = "Get car by ID")
    public ResponseEntity<Car> showCar(@PathVariable @Parameter(description = "Car ID") Long id) {
        var car = carService.getCar(id);

        return new ResponseEntity<>(car, HttpStatus.OK);
    }

    /**
     * Endpoint to get all cars.
     *
     * @return a ResponseEntity containing a list of all cars and an HTTP status code
     */
    @GetMapping("/all")
    @Operation(summary = "Get all cars", description = "Get all cars")
    public ResponseEntity<List<Car>> showAllCars() {
        var carList = carService.getAllCars();
        return new ResponseEntity<>(carList, HttpStatus.OK);
    }

    /**
     * Endpoint to add a new car.
     *
     * @param car the car to add
     * @return a ResponseEntity containing a success message and an HTTP status code
     */
    @PostMapping("/add")
    @Operation(summary = "Add car", description = "Add car")
    public ResponseEntity<String> addCar(@RequestBody @Parameter(description = "New car") Car car) {
        carService.create(car);
        return new ResponseEntity<>("Car was added", HttpStatus.CREATED);
    }

    /**
     * Endpoint to delete a car by its ID.
     *
     * @param id the ID of the car to delete
     * @return a ResponseEntity containing a success message and an HTTP status code
     */
    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete car", description = "Delete car")
    public ResponseEntity<String> deleteCar(@PathVariable @Parameter(description = "Car ID") Long id) {
        carService.deleteCar(id);
        return new ResponseEntity<>("Car with ID = " + id + " was deleted", HttpStatus.OK);
    }

    /**
     * Endpoint to update a car.
     *
     * @param id the ID of the car to update
     * @param car the updated car
     * @return a ResponseEntity containing a success message and an HTTP status code
     */
    @PutMapping("/update")
    @Operation(summary = "Update car", description = "Update car")
    public ResponseEntity<String> updateCar(@RequestParam @Parameter(description = "Car ID") Long id,
                                            @RequestBody @Parameter(description = "Updated car") Car car) {
        try {
            carService.update(id, car);
            return new ResponseEntity<>("Car was updated", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Car was not updated: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Endpoint to rent a car by a client.
     *
     * @param carId the ID of the car to rent
     * @param clientId the ID of the client renting the car
     * @param startDate the start date of the rental
     * @param endDate the end date of the rental
     * @return a ResponseEntity containing a success message and an HTTP status code
     */
    @PatchMapping("/rent-car")
    @Operation(summary = "Rent car by client", description = "Rent car by client")
    public ResponseEntity<String> rentCar(@RequestParam @Parameter(description = "Car ID for rent") Long carId,
                                          @RequestParam @Parameter(description = "Client ID renting the car") Long clientId,
                                          @RequestParam @Parameter(description = "Start renting date") LocalDate startDate,
                                          @RequestParam @Parameter(description = "End renting date") LocalDate endDate) {
        try {
            rentalService.rentCarByClient(carId, clientId, startDate, endDate);
            return new ResponseEntity<>("Car " + carId + " was rented by client " + clientId, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Car was not rented: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}

