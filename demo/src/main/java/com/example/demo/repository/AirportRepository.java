package com.example.demo.repository;

import com.example.demo.models.Airport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirportRepository extends JpaRepository<Airport,Integer> {
    Airport findByAirportNameIgnoreCase(String airportName);
}
