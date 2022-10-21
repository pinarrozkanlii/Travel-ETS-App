package com.example.demo.repository;


import com.example.demo.models.Arrival;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArrivalRepository extends JpaRepository<Arrival,Integer> {
}
