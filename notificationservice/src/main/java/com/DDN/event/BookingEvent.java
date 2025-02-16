package com.DDN.event;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookingEvent {
    private String customerName;
    private String email;
    // private String departureDate;
    // private String arrivalDate;
    // private LocalDate bookingDate;
    // private String paymentStatus;
    private String flightNumber;
}
