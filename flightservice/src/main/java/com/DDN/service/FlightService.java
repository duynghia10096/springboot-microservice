package com.DDN.service;
import java.util.List;
import com.DDN.model.FlightRequest;
import com.DDN.model.FlightResponse;

public interface FlightService {

    FlightResponse createFlight(FlightRequest flightRequest);

    List<FlightResponse> getAllFlights();

    FlightResponse getFlightByNumber(String flightNumber);

    public void reserveSeats(String flightNumber, int seats);

}
