package com.aleksei.rentcarapi.controllers;

import com.aleksei.rentcarapi.entity.Car;
import com.aleksei.rentcarapi.service.CarService;
import com.aleksei.rentcarapi.service.RentalService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class CarControllerTest {

    @Mock
    private RentalService rentalService;

    @Mock
    private CarService carService;

    @InjectMocks
    private CarController carController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void showCarReturnsCarWhenExists() {
        Car car = new Car();
        when(carService.getCar(1L)).thenReturn(car);
        ResponseEntity<Car> result = carController.showCar(1L);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(car, result.getBody());
    }

    @Test
    void showAllCarsReturnsListOfCars() {
        when(carService.getAllCars()).thenReturn(Arrays.asList(new Car(), new Car()));
        ResponseEntity<List<Car>> result = carController.showAllCars();
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(2, result.getBody() != null ? result.getBody().size() : 0);
    }

    @Test
    void addCarReturnsCreatedStatus() {
        Car car = new Car();
        ResponseEntity<String> result = carController.addCar(car);
        assertEquals(HttpStatus.CREATED, result.getStatusCode());
    }

    @Test
    void deleteCarReturnsOkStatus() {
        ResponseEntity<String> result = carController.deleteCar(1L);
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    void rentCarReturnsOkStatusWhenRentIsSuccessful() {
        ResponseEntity<String> result = carController.rentCar(1L, 1L, LocalDate.now(), LocalDate.now().plusDays(1));
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }
}