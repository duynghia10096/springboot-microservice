package com.DDN.external.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.DDN.external.request.PaymentRequest;

@FeignClient(name = "payment-service", url = "http://localhost:8083/v1/api/payments")
public interface PaymentService {
    @PostMapping("/flight")
    Long processFlightPayment(@RequestBody PaymentRequest paymentRequest);

    @PostMapping("/hotel")
    Long processHotelPayment(@RequestBody PaymentRequest paymentRequest);
}
