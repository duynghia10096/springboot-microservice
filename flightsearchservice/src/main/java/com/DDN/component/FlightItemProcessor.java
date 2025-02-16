package com.DDN.component;

import org.springframework.stereotype.Component;

import com.DDN.dto.FlightSearchEvent;
import com.DDN.entity.FLight;

import org.springframework.batch.item.ItemProcessor;

@Component
public class FlightItemProcessor implements ItemProcessor<FlightSearchEvent, FLight> {
    @Override
    public FLight process(FlightSearchEvent flight) {
        // flight.setPrice(Math.round(flight.getPrice() * 100.0) / 100.0);
        FLight elasticFlight = new FLight();
        elasticFlight.setId(flight.getFlightId());
        elasticFlight.setAmount(flight.getAmount());
        elasticFlight.setArrivalDate(flight.getArrivalDate());
        elasticFlight.setDepartureDate(flight.getDepartureDate());
        elasticFlight.setAvailableSeats(flight.getAvailableSeats());
        elasticFlight.setDestination(flight.getDestination());
        elasticFlight.setFlightNumber(flight.getFlightNumber());
        elasticFlight.setOrigin(flight.getOrigin());
        elasticFlight.setTotalSeats(flight.getTotalSeats());


        return elasticFlight;
    }
}
