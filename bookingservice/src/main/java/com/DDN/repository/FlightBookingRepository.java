package com.DDN.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.DDN.entity.FlightBooking;

public interface FlightBookingRepository extends JpaRepository<FlightBooking, Long> {

}
