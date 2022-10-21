package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "flight_reservations")
public class FlightReservation
{
    @Id
    @Column(name = "f_reservation_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer rsvpId;
    @Column(name = "date_time")
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime rsvpDate;
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "flight_id", referencedColumnName = "flight_id")
    private Flight flight;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private Client customer;

    public FlightReservation() {}

    public Integer getRsvpId() {
        return rsvpId;
    }

    public void setRsvpId(Integer rsvpId) {
        this.rsvpId = rsvpId;
    }

    public LocalDateTime getRsvpDate() {
        return rsvpDate;
    }

    public void setRsvpDate(LocalDateTime rsvpDate) {
        this.rsvpDate = rsvpDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public Client getCustomer() {
        return customer;
    }

    public void setCustomer(Client customer) {
        this.customer = customer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FlightReservation)) return false;
        FlightReservation that = (FlightReservation) o;
        return Objects.equals(getRsvpId(), that.getRsvpId()) &&
                Objects.equals(getRsvpDate(), that.getRsvpDate()) &&
                getStatus() == that.getStatus() &&
                Objects.equals(getFlight(), that.getFlight()) &&
                Objects.equals(getCustomer(), that.getCustomer());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRsvpId(), getRsvpDate(), getStatus(), getFlight().hashCode(), getCustomer().hashCode());
    }

    @Override
    public String toString() {
        return "Flight Reservation{" +
                "rsvpId=" + rsvpId +
                ", rsvpDate=" + rsvpDate +
                ", status=" + status.toString() +
                ", flight=" + flight.getFlightCode() +
                ", customer=" + customer.getId() +
                '}';
    }
}
