package com.example.demo.controllers;





import com.example.demo.models.Client;
import com.example.demo.models.HotelReservation;
import com.example.demo.models.Hotels;
import com.example.demo.repository.ClientRepository;
import com.example.demo.repository.HotelsRepository;
import com.example.demo.repository.HotelsReservationRepository;
import com.example.demo.services.HotelReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.html.HTMLObjectElement;

import java.util.List;

@Controller
@RequestMapping("/api")
public class HotelsReservationController {

   private HotelReservationService reservationService;

   public HotelsReservationController(HotelReservationService reservationService){
       this.reservationService=reservationService;
   }
   @PostMapping("/newUser")
    public void createClient(@RequestBody HotelReservation newReservation){
       reservationService.saveReservation(newReservation);
   }
   @GetMapping("/reservationId")
    public HotelReservation getReservationId(@PathVariable int reservationId){
       return reservationService.getReservationUserById(reservationId);
   }

    @GetMapping("/reservationList")
    public List<HotelReservation> getAllReservation(){
        return reservationService.getAllReservation();
    }

    @DeleteMapping("/deleteReservation")
    public void deleteReservation(@PathVariable int id){
       reservationService.deleteReservation(id);
    }



}
