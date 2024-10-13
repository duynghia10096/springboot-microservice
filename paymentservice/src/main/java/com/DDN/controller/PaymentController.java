package com.DDN.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.DDN.model.PaymentRequest;
import com.DDN.model.PaymentResponse;
import com.DDN.service.PaymentService;

@RestController
@RequestMapping("/v1/api/payments")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    // @PostMapping
    // public Long processPayment(@RequestBody PaymentRequest paymentRequest) {
    //     return paymentService.processPayment(paymentRequest);
    // }

    @PostMapping("/flight")
    public ResponseEntity<Long> processFlightPayment(@RequestBody PaymentRequest paymentRequest) {
        paymentRequest.setPaymentType("FLIGHT"); // Đặt loại thanh toán là FLIGHT
        Long paymentId = paymentService.processPayment(paymentRequest);
        return new ResponseEntity<>(paymentId, HttpStatus.CREATED);
    }

    @PostMapping("/hotel")
    public ResponseEntity<Long> processHotelPayment(@RequestBody PaymentRequest paymentRequest) {
        paymentRequest.setPaymentType("HOTEL"); // Đặt loại thanh toán là HOTEL
        Long paymentId = paymentService.processPayment(paymentRequest);
        return new ResponseEntity<>(paymentId, HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<PaymentResponse> getPaymentDetailsByBookingNumber(
            @RequestParam(name = "id") Long id) {
        return new ResponseEntity<>(
                paymentService.getPaymentDetailsByBookingId(id),
                HttpStatus.OK);
    }
}
