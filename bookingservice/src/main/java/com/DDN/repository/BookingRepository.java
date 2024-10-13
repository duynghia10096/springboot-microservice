package com.DDN.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.DDN.entity.Booking;

public interface BookingRepository extends JpaRepository<Booking, Long> {

}
