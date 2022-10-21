package com.example.demo.services;

import com.example.demo.models.errors.InvalidRequestException;
import com.example.demo.models.Client;
import com.example.demo.models.Flight;
import com.example.demo.models.FlightReservation;
import com.example.demo.models.Status;
import com.example.demo.repository.FlightReservationsRepository;
import com.example.demo.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class FlightReservationService {
    private final FlightReservationsRepository reservationRepository;
    private final ClientService customerService;
    private final FlightService flightService;

    @Autowired
    public FlightReservationService(final FlightReservationsRepository reservationRepository,
                              final ClientService customerService, final FlightService flightService)
    {
        this.reservationRepository = reservationRepository;
        this.customerService = customerService;
        this.flightService = flightService;
    }

    public Set<FlightReservation> getAllRSVPsByCustomerId(Integer customerId) throws IllegalArgumentException
    {
        if(Util.validateNumber(customerId))
        {
            Iterable<FlightReservation> iterable = reservationRepository.findAllRSVPByCustomerId(customerId);
            return iterableToReservationSet(iterable);
        }
        return null;
    }

    public boolean addRSVPByCustomerId(Map<String, Object> json) throws InvalidRequestException, NoSuchElementException
    {
        int customerId = -1;
        int flightId = -1;

        if(Util.verifyRSVPByCustomerId(json))
        {
            customerId = (Integer) json.get(Util.CUSTOMER_ID_JKEY);
            Optional<Client> customer = customerService.getClientById(customerId);
            if(customer == null) throw new NoSuchElementException("Customer does not exists with id="+customerId);

            flightId = (Integer)json.get(Util.FLIGHT_ID_JKEY);
            Flight flight = flightService.getFlightById(flightId);
            if(flight == null) throw new NoSuchElementException("Flight does not exists with id="+flightId);

            if(flight.getStatus().equalsIgnoreCase(Status.CANCELLED.toString()))
                throw new InvalidRequestException(HttpStatus.CHECKPOINT.value(), "Cannot rsvp to a cancelled flight.");
            /*if(flight.getCapacity() > flight.getCustomers().size())
            {
                reservationRepository.insertRSVPByCustomerId(Util.toDBDateTime(LocalDateTime.now()), Status.ACTIVE.toString(), customerId, flightId);
                flight.getCustomers().add(customer);
                Flight updated = flightService.updateFlight(flight);
                return updated.getFlightId().equals(flight.getFlightId());
            }*/
            else throw new InvalidRequestException(HttpStatus.EXPECTATION_FAILED.value(),"Flight is full. Cannot do rsvp.");
        }
        return false;
    }
    public boolean cancelRSVPByCustomerId(Integer rsvpId) throws IllegalArgumentException
    {
        if(Util.validateNumber(rsvpId))
        {
            Optional<FlightReservation> optionalReservation = reservationRepository.findById(rsvpId);
            if (!optionalReservation.isPresent())
                throw new IllegalArgumentException("No reservation exists with id=" + rsvpId);

            FlightReservation reservation = optionalReservation.get();

            reservation.setStatus(Status.CANCELLED);
            reservation.getFlight().getCustomers().remove(reservation.getCustomer());

            reservationRepository.save(reservation);
            return true;
        }
        return false;
    }

    public Set<FlightReservation> getAllCancelledRSVPs()
    {
        Iterable<FlightReservation> iterable = reservationRepository.findReservationsByStatus(Status.CANCELLED);
        return iterableToReservationSet(iterable);
    }

    public Set<FlightReservation> getAllActiveRSVPsByAirline(String airline) throws IllegalArgumentException
    {
        if(Util.validateAirlineName(airline))
        {
            Iterable<FlightReservation> iterable = reservationRepository.findActiveReservationsByAirline(airline, Status.ACTIVE.toString());
            return iterableToReservationSet(iterable);
        }
        return null;
    }

    public Set<FlightReservation> getAllCancelledRSVPsByAirline(String airline) throws IllegalArgumentException
    {
        if(Util.validateAirlineName(airline))
        {
            Iterable<FlightReservation> iterable = reservationRepository.findActiveReservationsByAirline(airline, Status.CANCELLED.toString());
            return iterableToReservationSet(iterable);
        }
        return null;
    }

    private Set<FlightReservation> iterableToReservationSet(Iterable<FlightReservation> iterable)
    {
        if(iterable != null)
        {
            Set<FlightReservation> reservations = new LinkedHashSet<>();
            iterable.forEach(reservation -> reservations.add(reservation));
            return reservations;
        }
        return null;
    }

}
