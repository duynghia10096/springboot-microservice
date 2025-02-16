package com.DDN.component;


import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.DDN.entity.FLight;
import com.DDN.repository.FlightRepository;

@Component
public class FlightItemWriter implements  ItemWriter<FLight> {
    @Autowired
    private FlightRepository flightRepository;


   @Override
    public void write(Chunk<? extends FLight> chunk) throws Exception {
        // Sử dụng repository để lưu các đối tượng từ chunk
        flightRepository.saveAll(chunk.getItems());
    }
   
}
