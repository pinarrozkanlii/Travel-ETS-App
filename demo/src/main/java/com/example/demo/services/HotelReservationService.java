package com.example.demo.services;

import com.example.demo.models.HotelReservation;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Collection;
import java.util.List;

public interface HotelReservationService {
    public List<HotelReservation> getAllReservation();

    public HotelReservation getReservationUserById(int resId);


    public void saveReservation(HotelReservation newReservation);

    public void deleteReservation(int resId);


}
