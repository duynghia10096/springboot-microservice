package com.DDN.component;


import java.util.List;

import org.springframework.batch.item.ItemReader;

import org.springframework.stereotype.Component;


import com.DDN.dto.FlightSearchEvent;

import com.DDN.external.FlightServiceClient;

@Component
public class FlightItemReader implements ItemReader<FlightSearchEvent> {
    private final FlightServiceClient flightServiceClient;

    private List<FlightSearchEvent> flights = null;
    private int nextIndex = 0;

    
    public FlightItemReader(FlightServiceClient flightServiceClient) {
        this.flightServiceClient = flightServiceClient;
    }

    @Override
    public FlightSearchEvent read() {
        // Lấy dữ liệu từ flight-service qua Feign Client khi danh sách flights chưa được load
        if (flights == null) {
            flights = flightServiceClient.getAllFlights();
        }

        // Trả về từng phần tử của danh sách
        if (nextIndex < flights.size()) {
            return flights.get(nextIndex++);
        }

        return null; // Kết thúc đọc
    }

}
