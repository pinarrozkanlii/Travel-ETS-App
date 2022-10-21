package com.example.demo.controllers;

import com.example.demo.models.Client;
import com.example.demo.services.ClientService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ClientController {
    private ClientService clientService;

    public ClientController(ClientService clientService){
        this.clientService=clientService;
    }
    @GetMapping("/useremail")
    public Client getClientByEmail(@PathVariable String email){
        return clientService.getClientByEmail(email);
    }
    @PostMapping
    public void createClient(@RequestBody Client newClient){
        clientService.createClient(newClient);
    }
    @DeleteMapping
    public void deleteClient(@PathVariable int id){ clientService.deleteClient(id);}

}
