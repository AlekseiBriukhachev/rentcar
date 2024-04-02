package com.aleksei.rentcarapi.repository;


import com.aleksei.rentcarapi.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
