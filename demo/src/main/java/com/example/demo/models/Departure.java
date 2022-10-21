package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "departure")
public class Departure {
    @Id
    @Column(name = "departure_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int departureId;

    @Column(name = "date_time")
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime departureDateTime;

    @JoinColumn(name = "airport_id", referencedColumnName = "airport_id")
    @OneToOne
    private Airport airport;

    public Departure() {}

    public Integer getDepartureId() {
        return departureId;
    }

    public void setDepartureId(Integer departureId) {
        this.departureId = departureId;
    }

    public LocalDateTime getDepartureDateTime() {
        return departureDateTime;
    }

    public void setDepartureDateTime(LocalDateTime departureDateTime) {
        this.departureDateTime = departureDateTime;
    }

    public Airport getAirport() {
        return airport;
    }

    public void setAirport(Airport airport) {
        this.airport = airport;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Departure)) return false;
        Departure departure = (Departure) o;
        return Objects.equals(getDepartureId(), departure.getDepartureId()) &&
                Objects.equals(getDepartureDateTime(), departure.getDepartureDateTime()) &&
                Objects.equals(getAirport().getAirportId(), departure.getAirport().getAirportId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDepartureId(), getDepartureDateTime(), getAirport().getAirportId());
    }

    @Override
    public String toString() {
        return "Departure{" +
                "departureId=" + departureId +
                ", departureDateTime=" + departureDateTime +
                ", airport=" + airport.getAirportId() +
                '}';
    }
}
