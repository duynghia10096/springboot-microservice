package com.DDN.service;



import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.DDN.entity.Payment;
import com.DDN.model.PaymentMode;
import com.DDN.model.PaymentRequest;
import com.DDN.model.PaymentResponse;
import com.DDN.repository.PaymentRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public Long processPayment(PaymentRequest paymentRequest) {
        log.info("Recording Payment Details: {}", paymentRequest);

        Payment payment = Payment.builder()
                .paymentDate(new Date())
                .paymentMode(paymentRequest.getPaymentType())
                .paymentStatus("SUCCESS")
                .bookingId(paymentRequest.getBookingId())
                .amount(paymentRequest.getAmount())
                .paymentType(paymentRequest.getPaymentType()) 
                .build();

        paymentRepository.save(payment);

        log.info("Payment Completed with Id: {}", payment.getPaymentId());

        return payment.getPaymentId();

    }

    @Override
    public PaymentResponse getPaymentDetailsByBookingId(Long bookingId) {
        log.info("Getting payment details for the Booking Id: {}", bookingId);

        Payment payment = paymentRepository.findByBookingId(bookingId);

        PaymentResponse paymentResponse = PaymentResponse.builder()
                .paymentId(payment.getPaymentId())
                .paymentMode(PaymentMode.valueOf(payment.getPaymentMode()))
                .paymentDate(payment.getPaymentDate())
                .bookingId(payment.getBookingId())
                .status(payment.getPaymentStatus())
                .amount(payment.getAmount())
                .build();

        return paymentResponse;
    }

}
