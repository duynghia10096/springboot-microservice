package com.DDN.external;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.DDN.dto.FlightSearchEvent;

@FeignClient(name = "flight-service", url = "http://localhost:8082/v1/api/flights")
public interface FlightServiceClient {
    @GetMapping
    public List<FlightSearchEvent> getAllFlights();
}
