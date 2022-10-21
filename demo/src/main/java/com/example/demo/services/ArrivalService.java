package com.example.demo.services;

import com.example.demo.models.Arrival;
import com.example.demo.repository.ArrivalRepository;
import com.example.demo.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ArrivalService {
    private final ArrivalRepository arrivalRepository;

    @Autowired
    public ArrivalService(final ArrivalRepository arrivalRepository)
    {
        this.arrivalRepository = arrivalRepository;
    }

    public Arrival getArrivalById(Integer arrivalId) throws IllegalArgumentException
    {
        if(Util.validateNumber(arrivalId))
        {
            Optional<Arrival> arrival = arrivalRepository.findById(arrivalId);
            return arrival.isPresent() ? arrival.get() : null;
        }
        return null;
    }

    public Arrival addArrival(Arrival arrival) throws IllegalArgumentException
    {
        if(Util.validateArrival(arrival))
        {
            return arrivalRepository.save(arrival);
        }
        return null;
    }
}
