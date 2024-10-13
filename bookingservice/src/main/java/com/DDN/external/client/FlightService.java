package com.DDN.external.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.DDN.exception.BookingException;

@FeignClient(name = "flight-service", url = "http://localhost:8082/v1/api/flights")
public interface FlightService {

    @PutMapping("/reserveSeats/{id}")
    public void reserveSeats(@PathVariable("id") String flightNumber,
            @RequestParam int seats);

    default void fallback(Exception e) {
        throw new BookingException("Flight Service is not available",
                "UNAVAILABLE",
                500);
    }
}