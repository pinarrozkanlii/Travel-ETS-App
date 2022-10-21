package com.example.demo.models;


import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "arrival")
public class Arrival {
    @Id
    @Column(name = "arrival_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int arrivalId;
    @Column(name = "date_time")
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime arrivalDateTime;
    @OneToOne
    @JoinColumn(name = "airport_id", referencedColumnName = "airport_id")
    private Airport airport;

    public Arrival() {}

    public Integer getArrivalId() {
        return arrivalId;
    }

    public void setArrivalId(Integer arrivalId) {
        this.arrivalId = arrivalId;
    }

    public LocalDateTime getArrivalDateTime() {
        return arrivalDateTime;
    }

    public void setArrivalDateTime(LocalDateTime arrivalDateTime) {
        this.arrivalDateTime = arrivalDateTime;
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
        if (!(o instanceof Arrival)) return false;
        Arrival that = (Arrival) o;
        return Objects.equals(getArrivalId(), that.getArrivalId()) &&
                Objects.equals(getArrivalDateTime(), that.getArrivalDateTime()) &&
                Objects.equals(getAirport().getAirportId(), that.getAirport().getAirportId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getArrivalId(), getArrivalDateTime(), getAirport().getAirportId());
    }

    @Override
    public String toString() {
        return "Arrival{" +
                "arrivalId=" + arrivalId +
                ", arrivalDateTime=" + arrivalDateTime +
                ", airport=" + airport.getAirportId() +
                '}';
    }
}
