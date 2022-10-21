package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
@Getter
@Setter
public class HotelReservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="reservationId")
    private int id;

    @Column(name="reservationRoom")
    private String room;

    @ManyToOne
    @JoinColumn(name = "reserved_hotel", referencedColumnName = "id")
    @JsonIgnore
    private Hotels reserved_hotel;

    @Column(name = "reservation_num_of_rooms")
    private int rooms;

    @Column(name="reservationPrice")
    private double price;

    @Column(name="numberOfPeople")
    private int people;

    @Column(name="numberOfChildren")
    private int children;

    @Column(name="arrivalDate")
    private Date arrivalDate;

    @Column(name="departureDate")
    private Date departureDate;

    @ManyToOne
    @JoinColumn(name="reservationUserId",referencedColumnName = "user_id")
    @JsonIgnore
    private Client userId;

    public HotelReservation(){

    }
    public HotelReservation(Hotels reserved_hotel,int id, String room, double price, int rooms, int people, int children, Date arrivalDate, Date departureDate, Client userId) {
        this.id = id;
        this.room = room;
        this.price = price;
        this.rooms = rooms;
        this.people = people;
        this.children = children;
        this.arrivalDate = arrivalDate;
        this.departureDate = departureDate;
        this.userId = userId;
        this.reserved_hotel=reserved_hotel;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", room='" + room + '\'' +
                ", price=" + price +
                ", rooms=" + rooms +
                ", persons=" + people +
                ", children=" + children +
                ", arrivalDate=" + arrivalDate +
                ", stayDays=" + departureDate +
                ", userId=" + userId +
                '}';
    }
}
