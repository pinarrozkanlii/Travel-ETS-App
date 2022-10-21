package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="users")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name="user_id")
    private int id;

    @Column(name="userName")
    private String username;

    @Column(name="userPassword")
    private String password;

    @Column(name="Email")
    private String email;

    //Flight reservation ekle
    @ManyToMany(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
    @JoinColumn(name="reservationId")
    private Collection<HotelReservation> reservations;


    @JsonIgnore
    @ManyToMany(mappedBy = "customers")
    private Set<Flight> flights;

    @JsonIgnore
    @OneToMany(mappedBy = "customer")
    private Set<FlightReservation> f_reservations;

    public Client(){

    }
    public Client(String username,String password,String email){
        this.username=username;
        this.password=password;
        this.email=email;

    }

    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id=id;
    }
    public String getUsername(){
        return username;
    }
    public String getPassword(){
        return password;
    }
    public void setUsername(String username){
        this.username=username;
    }
    public String setPassword(String password){
        return password;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public Collection<HotelReservation> getReservations() {
        return reservations;
    }

    public void setReservations(Collection<HotelReservation> reservations) {
        this.reservations = reservations;
    }

    public Set<Flight> getFlights() {
        return flights;
    }

    public void setFlights(Set<Flight> flights) {
        this.flights = flights;
    }

    public Set<FlightReservation> getFlightReservations() {
        return f_reservations;
    }

    public void setFlightReservations(Set<FlightReservation> f_reservations) {
        this.f_reservations = f_reservations;
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", reservations=" + reservations +
                '}';
    }
}







