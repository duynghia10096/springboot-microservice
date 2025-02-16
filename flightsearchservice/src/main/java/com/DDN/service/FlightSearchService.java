package com.DDN.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.DDN.dto.FlightSearchEvent;
import com.DDN.entity.FLight;
import com.DDN.repository.FlightRepository;

@Service
public class FlightSearchService {

    @Autowired
    private FlightRepository flightRepository;

    // @Autowired
    // private KafkaTemplate<String, Object> kafkaTemplate;

    public List<FLight> searchLights(String origin, String destination, String userId) {
        // FlightSearchEvent searchEvent = new FlightSearchEvent(userId, origin, destination, LocalDateTime.now().toString());
        // kafkaTemplate.send("flight_search_topic", searchEvent);
        
        return flightRepository.findByOriginAndDestination(origin, destination);
    }

}
