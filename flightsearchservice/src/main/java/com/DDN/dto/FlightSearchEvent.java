package com.DDN.dto;

import java.time.LocalDate;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FlightSearchEvent {
   
    private Long flightId;
    private String flightNumber;
    private String origin;
    private String destination;
    private LocalDate departureDate;
    private LocalDate arrivalDate;
    private Integer totalSeats;
    private Integer availableSeats;
    private double amount;
    
}
