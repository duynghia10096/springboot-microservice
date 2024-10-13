package com.DDN.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import com.DDN.entity.Flight;


public interface FlightRepository extends JpaRepository<Flight, Long> {

    Optional<Flight> findByFlightNumber(String flightNumber);
}
