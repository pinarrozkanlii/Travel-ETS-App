package com.example.demo.services;

import com.example.demo.models.Client;
import com.example.demo.repository.ClientRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.tools.OptionChecker;
import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Setter
@Getter
@Service
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;

    private static BCryptPasswordEncoder passwordEncoder;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    @Transactional
    public Client getClientByEmail(String email) {
        return clientRepository.findByEmail(email);
    }

    public Optional <Client> getClientById(int id){
        Optional<Client> user = clientRepository.findById(id);
        if(user.isPresent()){

            return user;

        }else{
            return null;
        }
        //return clientRepository.findById(id);
    }
    @Override
    @Transactional
    public void deleteClient(int id) {
        clientRepository.deleteById(id);
    }




    @Override
    @Transactional
    public void createClient(Client newUser) {
        Client user = new Client();


        user.setPassword(passwordEncoder.encode(newUser.getPassword()));
        user.setUsername(newUser.getUsername());
        user.setEmail(newUser.getEmail());
        clientRepository.save(user);
    }
    public List<Client> getClients(){
        return clientRepository.findAll();
    }







}
