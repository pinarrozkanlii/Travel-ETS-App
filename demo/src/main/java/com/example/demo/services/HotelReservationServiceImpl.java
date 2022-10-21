package com.example.demo.services;

import com.example.demo.models.HotelReservation;
import com.example.demo.repository.HotelsReservationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelReservationServiceImpl implements HotelReservationService {
    private HotelsReservationRepository reservRepo;

    public HotelReservationServiceImpl(HotelsReservationRepository reservRepo){
        this.reservRepo=reservRepo;
    }

    public List<HotelReservation> getAllReservation(){
        return reservRepo.findAll();
    }

    public HotelReservation getReservationUserById(int resId){
        return reservRepo.findById(resId);
    }


    public void saveReservation(HotelReservation newReservation){
        reservRepo.save(newReservation);
    }

    public void deleteReservation(int resId){
        reservRepo.deleteById(resId);
    }
}
