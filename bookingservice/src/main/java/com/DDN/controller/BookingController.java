package com.DDN.controller;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.DDN.model.BookingRequest;
import com.DDN.model.BookingResponse;
import com.DDN.model.FlightBookingRequest;
import com.DDN.model.HotelBookingRequest;
import com.DDN.service.BookingService;
import org.springframework.http.HttpStatus;

import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/v1/api/bookings")
@Log4j2
public class BookingController {

    private final BookingService bookingService;

    private final BookingService flightBookingService;

    private final BookingService hotelBookingService;

    public BookingController(BookingService bookingService,
            @Qualifier("flightBookingService") BookingService flightBookingService,
            @Qualifier("hotelBookingService") BookingService hotelBookingService) {
        this.bookingService = bookingService;
        this.hotelBookingService = hotelBookingService;
        this.flightBookingService = flightBookingService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String creatBooking(@RequestBody BookingRequest bookingRequest) {
        log.info("Create booking with request {} ", bookingRequest);
        return bookingService.reserveSeats(bookingRequest);

    }

    @PostMapping("/flight")
    public BookingResponse createFlightBooking(@RequestBody FlightBookingRequest flightBookingRequest) {
        log.info("save {} ", flightBookingRequest.getFlightNumber());
        return flightBookingService.createBooking(flightBookingRequest);
    }

    @PostMapping("/hotel")
    public BookingResponse createHotelBooking(@RequestBody HotelBookingRequest hotelBookingRequest) {
        log.info("save {} ", hotelBookingRequest.getHotelName());
        return hotelBookingService.createBooking(hotelBookingRequest);
    }

}
