package com.aleksei.rentcarapi.repository;


import com.aleksei.rentcarapi.entity.RentalHistory;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RentalHistoryRepository extends JpaRepository<RentalHistory, Long> {
}
