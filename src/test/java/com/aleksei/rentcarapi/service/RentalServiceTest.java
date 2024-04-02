package com.aleksei.rentcarapi.service;

import com.aleksei.rentcarapi.entity.Car;
import com.aleksei.rentcarapi.entity.Client;
import com.aleksei.rentcarapi.entity.RentalHistory;
import com.aleksei.rentcarapi.repository.RentalHistoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class RentalServiceTest {

    @Mock
    private CarService carService;

    @Mock
    private ClientService clientService;

    @Mock
    private RentalHistoryRepository rentalHistoryRepository;

    @InjectMocks
    private RentalService rentalService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void rentCarByClientSavesRentalHistory() {
        Long carId = 1L;
        Long clientId = 1L;
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = startDate.plusDays(1);

        Car car = new Car();
        Client client = new Client();

        when(carService.getCar(carId)).thenReturn(car);
        when(clientService.getClient(clientId)).thenReturn(client);

        rentalService.rentCarByClient(carId, clientId, startDate, endDate);

        verify(carService, times(1)).getCar(carId);
        verify(clientService, times(1)).getClient(clientId);
        verify(rentalHistoryRepository, times(1)).save(any(RentalHistory.class));
    }
}