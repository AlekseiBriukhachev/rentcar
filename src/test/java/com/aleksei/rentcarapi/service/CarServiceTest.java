package com.aleksei.rentcarapi.service;

import com.aleksei.rentcarapi.entity.Car;
import com.aleksei.rentcarapi.repository.CarRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CarServiceTest {


    @Mock
    private CarRepository carRepository;

    @InjectMocks
    private CarService carService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllCarsReturnsListOfCars() {
        when(carRepository.findAll()).thenReturn(Arrays.asList(new Car(), new Car()));
        List<Car> cars = carService.getAllCars();
        assertEquals(2, cars.size());
        verify(carRepository, times(1)).findAll();
    }

    @Test
    void getCarReturnsCarWhenExists() {
        Car car = new Car();
        when(carRepository.findById(1L)).thenReturn(Optional.of(car));
        Car result = carService.getCar(1L);
        assertEquals(car, result);
        verify(carRepository, times(1)).findById(1L);
    }

    @Test
    void getCarReturnsNullWhenDoesNotExist() {
        when(carRepository.findById(1L)).thenReturn(Optional.empty());
        Car result = carService.getCar(1L);
        assertNull(result);
        verify(carRepository, times(1)).findById(1L);
    }

    @Test
    void createSavesCar() {
        Car car = new Car();
        carService.create(car);
        verify(carRepository, times(1)).save(car);
    }

    @Test
    void deleteCarDeletesCar() {
        carService.deleteCar(1L);
        verify(carRepository, times(1)).deleteById(1L);
    }

    @Test
    void updateThrowsExceptionWhenCarDoesNotExist() {
        Car car = new Car();
        when(carRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> carService.update(1L, car));
        verify(carRepository, times(1)).findById(1L);
    }

    @Test
    void updateUpdatesCarWhenExists() {
        Car existingCar = new Car(2L, "Audi", "A6", 2016, "White", 20000L);
        when(carRepository.findById(1L)).thenReturn(Optional.of(existingCar));
        Car updatedCar = new Car(1L,"BMW", "X5", 2015, "Black", 10000L);
        when(carRepository.findById(1L)).thenReturn(Optional.of(existingCar));
        carService.update(1L, updatedCar);
        verify(carRepository, times(1)).save(existingCar);
        verify(carRepository, times(1)).findById(1L);
    }
}