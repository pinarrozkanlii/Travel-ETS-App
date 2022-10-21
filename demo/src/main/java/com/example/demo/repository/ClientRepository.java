package com.example.demo.repository;

import com.example.demo.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface ClientRepository extends JpaRepository<Client,Integer> {
    Client findByEmail(String email);
    @Modifying
    @Transactional
    @Query(value = "delete from customers_flights cf where cf.user_id = :c_id ; " +
            "delete from flight_reservations r where r.user_id = :c_id ; " +
            "delete from user where user_id = :c_id", nativeQuery = true)
    void deleteClientByClientId(@Param("c_id") Integer id);

}
