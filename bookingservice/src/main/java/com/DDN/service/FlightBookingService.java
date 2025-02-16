package com.DDN.service;

import java.time.LocalDate;
import java.util.HashMap;

import java.util.Map;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.DDN.entity.BookingStatus;
import com.DDN.entity.FlightBooking;

import com.DDN.external.client.FlightService;
// import com.DDN.external.client.PaymentService;

import com.DDN.model.BookingRequest;
import com.DDN.model.BookingResponse;
import com.DDN.model.FlightBookingRequest;
import com.DDN.model.FlightBookingResponse;
import com.DDN.repository.FlightBookingRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
// @RequiredArgsConstructor
@Qualifier("flightBookingService")
@Slf4j
public class FlightBookingService implements BookingService {

    @Autowired
    private FlightBookingRepository flightBookingRepository;

    @Autowired
    // @Qualifier("flightService")
    private FlightService flightService;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    // @Autowired
    // private PaymentService paymentService;

    // private final WebClient webClient;

    @Override
    public BookingResponse createBooking(BookingRequest bookingRequest) {

        if (!(bookingRequest instanceof FlightBookingRequest)) {
            throw new IllegalArgumentException("Invalid booking type");
        }

        FlightBooking flightBooking = mapToFlightBooking(bookingRequest);

        // validate flight Booking

        log.info("create Booking for user {}", bookingRequest.getPassengerName());

        // PaymentRequest paymentRequest = PaymentRequest
        // .builder()
        // .bookingId(flightBooking.getId())
        // .amount(flightBooking.getAmount())
        // .paymentMode(flightBooking.getPaymentMode())
        // .build();

        // Long paymentId = paymentService.processFlightPayment(paymentRequest);

        // if(paymentId != null) {
        // log.info("Successful Payment!!!");
        // } else {
        // throw new BookingException("Payment is failed",
        // "Failed",
        // 500);
        // }
        // save the details
        flightBooking = flightBookingRepository.save(flightBooking);

        log.info("booking status is {} ", flightBooking.getStatus());

        try {
            Map<String, String> notification = new HashMap();
            notification.put("customerName", flightBooking.getPassengerName());
            notification.put("email", flightBooking.getEmail());
            notification.put("flightNumber", flightBooking.getFlightNumber());
            kafkaTemplate.send("booking-notification", new ObjectMapper().writeValueAsString(notification));
        } catch (JsonProcessingException e) {
            e.printStackTrace(); // Xử lý ngoại lệ hoặc ghi log lỗi
        }

        // check for seat availability and reduce the seats for flight
        flightService.reserveSeats(flightBooking.getFlightNumber(),
                flightBooking.getSeats());
        log.info("Seats are reserverd for booking {}", flightBooking.getFlightNumber());

        FlightBookingResponse flightBookingResponse = new FlightBookingResponse();
        BeanUtils.copyProperties(flightBooking, flightBookingResponse);

        return flightBookingResponse;
    }

    private FlightBooking mapToFlightBooking(BookingRequest bookingRequest) {
        FlightBookingRequest flightBookingRequest = (FlightBookingRequest) bookingRequest;

        FlightBooking flightBooking = new FlightBooking();

        flightBooking.setBookingNumber(UUID.randomUUID().toString());
        flightBooking.setFlightNumber(flightBookingRequest.getFlightNumber());
        flightBooking.setEmail(bookingRequest.getEmail());
        flightBooking.setBookingDate(LocalDate.now());
        flightBooking.setPassengerName(flightBookingRequest.getPassengerName());

        flightBooking.setAmount(flightBookingRequest.getAmount());
        flightBooking.setPaymentMode(flightBookingRequest.getPaymentMode().name());
        flightBooking.setStatus(BookingStatus.CREATED.name());

        flightBooking.setSeats(flightBookingRequest.getSeats());

        return flightBooking;

    }

    @Override
    public String reserveSeats(BookingRequest bookingRequest) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'reserveBooking'");
    }

}
