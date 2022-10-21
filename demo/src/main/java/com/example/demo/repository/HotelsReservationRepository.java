package com.example.demo.repository;

import com.example.demo.models.HotelReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelsReservationRepository extends JpaRepository<HotelReservation,Integer> {
    HotelReservation findById(int resId);

}
