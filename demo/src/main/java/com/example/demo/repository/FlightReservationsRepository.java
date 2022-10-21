package com.example.demo.repository;

import com.example.demo.models.FlightReservation;
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
public interface FlightReservationsRepository extends JpaRepository<FlightReservation,Integer> {

    @Query(value = "select * from flight_reservations where user_id = :user_id", nativeQuery = true)
    Set<FlightReservation> findAllRSVPByCustomerId(@Param("user_id") Integer customerId);


    Set<FlightReservation> findReservationsByStatus(Status status);


    @Query(value = "select * from flight_reservations r inner join flights f on f.flight_id in " +
            "(select flight_id from flights where airplane_id in " +
            "(select ap.airplane_id from airplanes ap inner join airlines al " +
            "on al.airline_id = (select airline_id  from airlines where lower(airline_name) = lower(:airline)) " +
            "and al.airline_id = ap.airline_id )) and r.flight_id = f.flight_id and lower(r.status) = lower(:status)", nativeQuery = true)
    Set<FlightReservation> findActiveReservationsByAirline(@Param("airline")String airline, @Param("status") String status);

    @Modifying
    @Transactional
    @Query(value = "insert into flight_reservations (date_time, status, user_id, flight_id) " +
            "values (:date_time, :status, :user_id, :flight_id)", nativeQuery = true)
    void insertRSVPByCustomerId(@Param("date_time") LocalDateTime dateTime, @Param("status")String status,
                                @Param("user_id")int customerId, @Param("flight_id")int flightId);
}
