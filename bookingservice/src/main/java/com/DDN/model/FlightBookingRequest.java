package com.DDN.model;

import lombok.Data;

@Data
public final class FlightBookingRequest extends BookingRequest {
    private String flightNumber;
    private int seats;
}
