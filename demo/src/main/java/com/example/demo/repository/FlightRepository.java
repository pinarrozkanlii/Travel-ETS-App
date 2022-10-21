package com.example.demo.repository;

import com.example.demo.models.Flight;
import com.example.demo.models.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Set;

@Repository
public interface FlightRepository extends JpaRepository<Flight,Integer> {
    @Query(value = "select * from flights f inner join departure s" +
            " on s.departure_id = f.departure_id and s.date_time >= :date_time1 and s.date_time < :date_time2", nativeQuery = true)
    Set<Flight> findByCurrentDateTime(@Param("date_time1") LocalDateTime dateTime1,
                                      @Param("date_time2") LocalDateTime dateTime2);


    @Query(value = "select * from flights f inner join departure s" +
            " on s.departure_id = f.departure_id and s.date_time >= :date_time", nativeQuery = true)
    Set<Flight> findByDate(@Param("date_time") LocalDateTime dateTime);


    @Query(value = "select f from Flight f where f.fare <= :fare and f.fare >= 0")
    Set<Flight> findByFare(@Param("fare") Float fare);


    Set<Flight> findFlightsByStatus(Status status);

    @Modifying
    @Transactional
    @Query(value = "delete from customers_flights cf where cf.flight_id = :flight_id ; " +
            "update flights f set f.status = :_status where f.flight_id = :flight_id ; " +
            "update flight_reservations r set r.status = :_status where r.status = 'ACTIVE' and r.flight_id = :flight_id ; ",
            nativeQuery = true)
    void deleteCustomersRSVPsByFlightId(@Param("flight_id") Integer flightId, @Param("_status") String flightStatus);

    @Modifying
    @Transactional
    @Query(value = "update flights f set f.status = :_status where f.flight_id = :flight_id", nativeQuery = true)
    void changeFlightStatus(@Param("_status") String status, @Param("flight_id")Integer flightId);
}
