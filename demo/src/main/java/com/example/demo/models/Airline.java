package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name="airlines")
@Getter
@Setter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Airline implements Serializable {
    @Id
    @Column(name = "airline_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer airlineId;
    @Column(name = "airline_name")
    private String airlineName;
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "airline")
    private Set<Airplane> airplanes;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Airline)) return false;
        Airline airline = (Airline) o;
        return Objects.equals(getAirlineId(), airline.getAirlineId()) &&
                Objects.equals(getAirlineName(), airline.getAirlineName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAirlineId(), getAirlineName());
    }

    @Override
    public String toString() {
        return "Airline{" +
                "airlineId=" + airlineId +
                ", airlineName='" + airlineName + '\'' +
                ", airplanes=" + airplanes +
                '}';
    }

}
