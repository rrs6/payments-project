package com.processpayment.paymentprocessor.consumers;

import org.springframework.stereotype.Component;

import com.processpayment.paymentprocessor.dtos.PaymentProcessPayload;

import io.awspring.cloud.sqs.annotation.SqsListener;

@Component
public class ProcessPayment {
    
    @SqsListener("payment-process")
    public void listen(PaymentProcessPayload message) {

    }

}