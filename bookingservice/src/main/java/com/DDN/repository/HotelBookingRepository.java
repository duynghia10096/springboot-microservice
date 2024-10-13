package com.DDN.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.DDN.entity.HotelBooking;

public interface HotelBookingRepository extends JpaRepository<HotelBooking, Long> {

}
