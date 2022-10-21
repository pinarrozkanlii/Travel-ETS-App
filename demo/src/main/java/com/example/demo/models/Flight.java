package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name="flights")
public class Flight {
    @Id
    @Column(name = "flight_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer flightId;
    @Column(name = "flight_code", nullable = false)
    private String flightCode;
    @JoinColumn(name = "departure_id", referencedColumnName = "departure_id")
    @OneToOne
    private Departure departure;
    @JoinColumn(name = "arrival_id", referencedColumnName = "arrival_id")
    @OneToOne
    private Arrival arrival;
    @OneToOne
    @JoinColumn(name = "airplane_id", referencedColumnName = "airplane_id")
    private Airplane airplane;
    @Transient
    private Integer availableSeat;
    @Column(name = "fare")
    private Float fare;
    @Column(name = "capacity", updatable = false, nullable = false)
    private Integer capacity;
    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;
    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "customers_flights",
            inverseJoinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "user_id")},
            joinColumns = {@JoinColumn(name = "flight_id", referencedColumnName = "flight_id")}
    )
    private Set<Client> customers;

    public Flight() {}

    public Integer getFlightId() {
        return flightId;
    }

    public void setFlightId(Integer flightId) {
        this.flightId = flightId;
    }

    public String getFlightCode() {
        return flightCode;
    }

    public void setFlightCode(String flightCode) {
        this.flightCode = flightCode;
    }

    public Departure getDeparture() {
        return departure;
    }

    public void setDeparture(Departure departure) {
        this.departure = departure;
    }

    public Arrival getArrival() {
        return arrival;
    }

    public void setArrival(Arrival arrival) {
        this.arrival = arrival;
    }

    public Airplane getAirplane() {
        return airplane;
    }

    public void setAirplane(Airplane airplane) {
        this.airplane = airplane;
    }

    public Integer getAvailableSeat() {
        return availableSeat;
    }

    public void setAvailableSeat(Integer availableSeat) {
        this.availableSeat = availableSeat;
    }

    public Float getFare() {
        return fare;
    }

    public void setFare(Float fare) {
        this.fare = fare;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Set<Client> getCustomers() {
        return customers;
    }

    public void setCustomers(Set<Client> customers) {
        this.customers = customers;
    }

    public String getStatus() {
        return status.toString();
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Flight)) return false;
        Flight flight = (Flight) o;
        return Objects.equals(getFlightId(), flight.getFlightId()) &&
                Objects.equals(getFlightCode(), flight.getFlightCode()) &&
                Objects.equals(getArrival(), flight.getArrival()) &&
                Objects.equals(getDeparture(), flight.getDeparture()) &&
                Objects.equals(getAirplane(), flight.getAirplane());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFlightId(), getFlightCode(), getArrival().hashCode(), getDeparture().hashCode(), getAirplane().hashCode());
    }

    @Override
    public String toString() {
        return "Flight{" +
                "flightId=" + flightId +
                ", flightCode='" + flightCode + '\'' +
                ", airplane=" + airplane +
                ", availableSeat=" + availableSeat +
                ", fare=" + fare +
                ", capacity=" + capacity +
                ", status=" + status +
                '}';
    }

}
