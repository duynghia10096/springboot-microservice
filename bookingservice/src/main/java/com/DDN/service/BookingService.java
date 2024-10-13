package com.DDN.service;

import com.DDN.model.BookingRequest;
import com.DDN.model.BookingResponse;

public interface BookingService {

    public BookingResponse createBooking(BookingRequest bookingRequest);

    public String reserveSeats(BookingRequest bookingRequest);

}