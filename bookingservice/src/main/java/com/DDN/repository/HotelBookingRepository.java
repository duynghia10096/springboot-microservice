package com.DDN.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.DDN.entity.HotelBooking;


@Repository
public interface HotelBookingRepository extends JpaRepository<HotelBooking, Long> {

}
