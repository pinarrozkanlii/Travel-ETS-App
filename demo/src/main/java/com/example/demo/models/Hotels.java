package com.example.demo.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;



@Entity
@Table(name="hotels")
@Getter
@Setter
public class Hotels {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="hotelName")
    private String hotelName;

    @Column(name="numberOfstars")
    private int stars;

    @Column(name="latitude")
    private double latitude;

    @Column(name="longitude")
    private double longitude;

    @Column(name="avaliableRooms")
    private int avaliableRooms;

    @Column(name="price")
    private double price;

    @Column(name="city")
    private String city;

    @Column(name="country")
    private String country;

    @Column(name="address")
    private String address;

    public Hotels(){}

    public Hotels(String hotelName,int stars,double latitude,double longitude,int avaliableRooms,double price,String city,String country,String address){
        this.hotelName=hotelName;
        this.stars=stars;
        this.latitude=latitude;
        this.longitude=longitude;
        this.price=price;
        this.avaliableRooms=avaliableRooms;
        this.city=city;
        this.country=country;
        this.address=address;
    }
    @Override
    public String toString() {
        return "Hotel{" +
                "id=" + id +
                ", hotelName='" + hotelName + '\'' +
                ", numberOfStars='" + stars + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitude=" + longitude +
                ", avaliableRooms='" + avaliableRooms + '\'' +
                ", price='" + price + '\'' +
                ", city='" + city + '\'' +
                ", country=" + country +
                ", address='" + address + '\'' +

                '}';
    }


}
