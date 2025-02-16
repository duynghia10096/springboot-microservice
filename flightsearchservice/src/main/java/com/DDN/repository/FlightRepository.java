package com.DDN.repository;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import org.springframework.stereotype.Repository;

import com.DDN.entity.FLight;

@Repository
public interface FlightRepository extends ElasticsearchRepository<FLight, Long> {
    List<FLight> findByOriginAndDestination(String origin, String destination);
}
