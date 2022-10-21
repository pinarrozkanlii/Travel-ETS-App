package com.example.demo.services;

import com.example.demo.models.Departure;
import com.example.demo.repository.DepartureRepository;
import com.example.demo.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DepartureService {
    private final DepartureRepository departureRepository;

    @Autowired
    public DepartureService(final DepartureRepository departureRepository)
    {
        this.departureRepository = departureRepository;
    }

    public Departure getDepartureById(Integer departureId) throws IllegalArgumentException
    {
        if(Util.validateNumber(departureId))
        {
            Optional<Departure> departure = departureRepository.findById(departureId);
            return departure.isPresent() ? departure.get() : null;
        }
        return null;
    }

    public Departure addDeparture(Departure departure) throws IllegalArgumentException
    {
        if(Util.validateDeparture(departure))
        {
            return departureRepository.save(departure);
        }
        return null;
    }
}
