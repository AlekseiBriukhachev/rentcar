package com.aleksei.rentcarapi.service;

import com.aleksei.rentcarapi.entity.RentalHistory;
import com.aleksei.rentcarapi.repository.RentalHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

/**
 * RentalService is a service class that provides methods for managing car rentals in the system.
 * It uses CarService, ClientService, and RentalHistoryRepository to perform business logic and database operations.
 */
@Service
@Transactional(readOnly = true)
public class RentalService {
    private final CarService carService;
    private final ClientService clientService;
    private final RentalHistoryRepository rentalHistoryRepository;

    /**
     * Constructs a new RentalService with the given CarService, ClientService, and RentalHistoryRepository.
     *
     * @param carService the CarService to use for car-related operations
     * @param clientService the ClientService to use for client-related operations
     * @param rentalHistoryRepository the RentalHistoryRepository to use for rental history-related database operations
     */
    @Autowired
    public RentalService(CarService carService, ClientService clientService, RentalHistoryRepository rentalHistoryRepository) {
        this.carService = carService;
        this.clientService = clientService;
        this.rentalHistoryRepository = rentalHistoryRepository;
    }

    /**
     * Rents a car to a client for a specified period.
     *
     * @param carId the ID of the car to rent
     * @param clientId the ID of the client renting the car
     * @param startDate the start date of the rental period
     * @param endDate the end date of the rental period
     */
    @Transactional
    public void rentCarByClient(Long carId, Long clientId, LocalDate startDate, LocalDate endDate) {
        var car = carService.getCar(carId);
        var client = clientService.getClient(clientId);

        var rentalHistory = new RentalHistory();
        rentalHistory.setCar(car);
        rentalHistory.setClient(client);
        rentalHistory.setStartDate(startDate);
        rentalHistory.setEndDate(endDate);
        rentalHistoryRepository.save(rentalHistory);
    }
}
