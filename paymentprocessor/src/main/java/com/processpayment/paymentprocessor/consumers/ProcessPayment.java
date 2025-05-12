package com.processpayment.paymentprocessor.consumers;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.processpayment.paymentprocessor.dtos.PaymentProcessPayload;
import com.processpayment.paymentprocessor.dtos.PaymentStatusProcessed;
import com.processpayment.paymentprocessor.producers.PaymentStatusProcessedProducer;
import com.processpayment.paymentprocessor.services.PaymentProcessorService;

import io.awspring.cloud.sqs.annotation.SqsListener;

@Component
public class ProcessPayment {
    private final PaymentProcessorService paymentProcessorService;
    private final PaymentStatusProcessedProducer paymentStatusProcessed;
    
    public ProcessPayment(PaymentProcessorService paymentProcessorService, PaymentStatusProcessedProducer paymentStatusProcessed) {
        this.paymentProcessorService = paymentProcessorService;
        this.paymentStatusProcessed = paymentStatusProcessed;
    }

    @SqsListener("process-payment")
    public void listen(PaymentProcessPayload message) throws JsonProcessingException {
        PaymentStatusProcessed statusPayment = paymentProcessorService.processing(message);
        paymentStatusProcessed.sendProcessedPayment(statusPayment);
    }

}