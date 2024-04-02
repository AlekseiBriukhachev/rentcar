package com.aleksei.rentcarapi.repository;


import com.aleksei.rentcarapi.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CarRepository extends JpaRepository<Car, Long> {
}
