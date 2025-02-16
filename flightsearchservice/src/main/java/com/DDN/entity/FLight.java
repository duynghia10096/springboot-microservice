package com.DDN.entity;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import lombok.Data;


@Document(indexName = "flights")
@Data
public class FLight {
    @Id
    private Long id;

    @Field(type = FieldType.Text)
    private String flightNumber;

    @Field(type = FieldType.Keyword)
    private String origin;

    @Field(type = FieldType.Keyword)
    private String destination;

    @Field(type = FieldType.Date)
    private LocalDate departureDate;

    @Field(type = FieldType.Date)
    private LocalDate arrivalDate;

    @Field(type = FieldType.Integer)
    private Integer totalSeats;

    @Field(type = FieldType.Integer)
    private Integer availableSeats;

    @Field(type = FieldType.Double)
    private Double amount;

    

}
