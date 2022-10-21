package com.example.demo.services;

import com.example.demo.models.Client;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface ClientService {
    public abstract void createClient(Client client);
    public abstract Client getClientByEmail(String email);
    //public int getLoggedClientId();

    public abstract Optional<Client> getClientById(int id);

    public abstract List<Client> getClients();
    public abstract void deleteClient(int id);

}
