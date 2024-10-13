package com.DDN.external.request;

import com.DDN.model.PaymentMode;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor 
@AllArgsConstructor
public class PaymentRequest {

    private Long bookingId;
    private double amount;
    private String paymentMode;

  
}