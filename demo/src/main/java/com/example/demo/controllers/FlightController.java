package com.example.demo.controllers;

import com.example.demo.models.errors.InvalidRequestException;
import com.example.demo.models.errors.InvalidRequestExceptionResponse;
import com.example.demo.models.*;
import com.example.demo.services.*;
import com.example.demo.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

@RestController
@RequestMapping("/api")
public class FlightController
{
    private final AirportService airportService;
    private final AirlineService airlineService;
    private final AirplaneService airplaneService;
    private final ClientService customerService;
    private final FlightService flightService;
    private final FlightReservationService reservationService;

    @Autowired
    public FlightController(final AirportService airportService, final AirlineService airlineService,
                          final AirplaneService airplaneService, final ClientService customerService,
                          final FlightService flightService, final FlightReservationService reservationService)
    {
        this.airportService = airportService;
        this.airlineService = airlineService;
        this.airplaneService = airplaneService;
        this.customerService = customerService;
        this.flightService = flightService;
        this.reservationService = reservationService;
    }

    @GetMapping(value = "/airports", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Airport>> getAirports()
    {
        List<Airport> airports = airportService.getAirports();
        return airports != null ? new ResponseEntity<>(airports, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/airport/{airportName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Airport> getAirportByName(@PathVariable String airportName)
    {
        try
        {
            Airport airport = null;
            if(Util.validateAirportName(airportName))
            {
                airport = airportService.getAirportByName(airportName);
            }
            return airport != null ? new ResponseEntity<>(airport, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch (IllegalArgumentException ex)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }

    @GetMapping(value = "/airlines", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Airline>> getAirlines()
    {
        List<Airline> airlines = airlineService.getAirlines();
        return airlines != null ? new ResponseEntity<>(airlines, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/airline/{airlineName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Airline> getAirlineByName(@PathVariable String airlineName)
    {
        try
        {
            Airline airline = null;
            if(Util.validateAirlineName(airlineName))
            {
                airline = airlineService.getAirlineByName(airlineName);
            }
            return airline != null ? new ResponseEntity<>(airline, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch (IllegalArgumentException ex)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }

    @GetMapping(value = "/airline/{airlineName}/airplanes", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<Airplane>> getAirplanesByAirlineName(@PathVariable String airlineName)
    {
        try
        {
            Set<Airplane> airplanes = null;
            if(Util.validateAirlineName(airlineName)) airplanes = airlineService.getAirplanesByAirlineName(airlineName);
            return airplanes != null ? new ResponseEntity<>(airplanes, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch (IllegalArgumentException ex)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }

    @GetMapping(value = "/airplanes", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Airplane>> getAirplanes()
    {
        List<Airplane> airplanes = airplaneService.getAirplanes();
        return airplanes != null ? new ResponseEntity<>(airplanes, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/customers", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Client>> getCustomers()
    {
        List<Client> customers = customerService.getClients();
        return customers != null ? new ResponseEntity<>(customers, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/customer/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Client> getCustomerByEmail(@PathVariable String email)
    {
        Client customer = customerService.getClientByEmail(email);
        return customer != null ? new ResponseEntity<>(customer, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/flights", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<Flight>> getFlights()
    {
        Set<Flight> flights = flightService.getFlights();
        return flights != null ? new ResponseEntity<>(flights, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/flight/{flightId}")
    public ResponseEntity<Flight> get(@PathVariable Integer flightId)
    {
        try
        {
            Flight flight = null;
            if(Util.validateNumber(flightId)) flight = flightService.getFlightById(flightId);
            return flight != null ? new ResponseEntity<>(flight, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch (IllegalArgumentException ex)
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

    @GetMapping(value = "/flights/today", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<Flight>> getFlightsByToday()
    {
        Set<Flight> flights = flightService.getFlightsForToday();
        return flights != null ? new ResponseEntity<>(flights, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/flights/{date}")
    public ResponseEntity<Set<Flight>> getFlightsByDate(@PathVariable String date)
    {
        try
        {
            Set<Flight> flights = flightService.getFlightsByDate(Util.stringDateToDateTime(date));
            return flights != null ? new ResponseEntity<>(flights, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        catch (IllegalArgumentException ex)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage(), ex);
        }
    }

    @GetMapping(value = "/flights/fare/{fare}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<Flight>> getFlightsByFare(@PathVariable Float fare)
    {
        Set<Flight> flights = flightService.getFlightsByFare(fare);
        return flights != null && !flights.isEmpty() ? new ResponseEntity<>(flights, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/flights/status/{status}")
    public ResponseEntity<Set<Flight>> getFlightsByStatus(@PathVariable String status)
    {
        try
        {
            Set<Flight> flights = flightService.getFlightsByStats(status);
            return flights != null ? new ResponseEntity<>(flights, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        catch (IllegalArgumentException ex)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage(), ex);
        }
    }

    @GetMapping(value = "/rsvps/customer/{customerId}")
    public ResponseEntity<Set<FlightReservation>> getAllRSVPsByCustomerId(@PathVariable Integer customerId)
    {
        try
        {
            Set<FlightReservation> reservations = reservationService.getAllRSVPsByCustomerId(customerId);
            return reservations != null ? new ResponseEntity<>(reservations, HttpStatus.OK) :
                    new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch (IllegalArgumentException ex)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage(), ex);
        }
    }

    @GetMapping(value = "/rsvps/cancelled", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<FlightReservation>> getAllCancelledRSVPs()
    {
        Set<FlightReservation> reservations = reservationService.getAllCancelledRSVPs();
        return reservations != null ? new ResponseEntity<>(reservations, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/rsvps/{airline}/active", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<FlightReservation>> getAllActiveRSVPsByAirline(@PathVariable String airline)
    {
        try
        {
            Set<FlightReservation> reservations = reservationService.getAllActiveRSVPsByAirline(airline);
            return reservations != null ? new ResponseEntity<>(reservations, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        catch (IllegalArgumentException ex)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage(), ex);
        }
    }

    @GetMapping(value = "/rsvps/{airline}/cancelled", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<FlightReservation>> getAllCancelledRSVPsByAirline(@PathVariable String airline)
    {
        try
        {
            Set<FlightReservation> reservations = reservationService.getAllCancelledRSVPsByAirline(airline);
            return reservations != null ? new ResponseEntity<>(reservations, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        catch (IllegalArgumentException ex)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage(), ex);
        }
    }

    @PostMapping(value = "/flight", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Flight> insertFlight(@RequestBody Flight flight)
    {
        try
        {
            return new ResponseEntity<>(flightService.addFlight(flight), HttpStatus.OK);
        }
        catch (IllegalArgumentException ex)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage(), ex);
        }
        catch (NullPointerException ex)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage(), ex);
        }
        catch (Exception ex)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage(), ex);
        }
    }

    @PostMapping(value = "/rsvp/customer", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> addRSVPByCustomerId(@RequestBody Map<String, Object> json)
    {
        try
        {
            return reservationService.addRSVPByCustomerId(json) ? new ResponseEntity<>(true, HttpStatus.OK) :
                    new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
        catch (IllegalArgumentException ex)
        {
            InvalidRequestException response = new InvalidRequestException(HttpStatus.CHECKPOINT.value(), ex.getMessage());
            return new ResponseEntity<>(response, HttpStatus.CHECKPOINT);
        }
        catch (InvalidRequestException ex)
        {
            InvalidRequestExceptionResponse response = new InvalidRequestExceptionResponse(ex);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        catch (NoSuchElementException ex)
        {
            InvalidRequestExceptionResponse response = new InvalidRequestExceptionResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/airport", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Airport> addAirport(@RequestBody Airport airport)
    {
        Airport addedAirport = airportService.addAirport(airport);
        return addedAirport != null ? new ResponseEntity<>(airport, HttpStatus.CREATED) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping(value = "/rsvp/cancel/{rsvpId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> cancelRSVPByCustomerId(@PathVariable Integer rsvpId)
    {
        try
        {
            boolean isCancelled = reservationService.cancelRSVPByCustomerId(rsvpId);
            return isCancelled ? new ResponseEntity<>(true, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch (IllegalArgumentException ex)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage(), ex);
        }
    }

    @PutMapping(value = "/flight/cancel/{flightId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> cancelFlightById(@PathVariable Integer flightId)
    {
        return flightService.cancelFlight(flightId) ? new ResponseEntity<>(true, HttpStatus.OK) : new ResponseEntity<>(false, HttpStatus.EXPECTATION_FAILED);
    }


}

