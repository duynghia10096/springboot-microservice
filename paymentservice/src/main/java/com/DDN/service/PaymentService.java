package com.DDN.service;

import com.DDN.model.PaymentRequest;
import com.DDN.model.PaymentResponse;

public interface PaymentService {

    Long processPayment(PaymentRequest paymentRequest);

    PaymentResponse getPaymentDetailsByBookingId(Long bookingId);

}
