package com.transactionvalidator.transaction_validator.producers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.transactionvalidator.transaction_validator.db.entities.Payment;

import io.awspring.cloud.sqs.operations.SqsTemplate;

@Component
public class CreatePaymentProcess {
    private Payment payment;

    @Autowired
    private SqsTemplate sqsTemplate;
    private final String SQS_url = "https://localhost.localstack.cloud:4566/000000000000/payment-process";

    public void setPayload(Payment payment) {
        this.payment = payment;
    }

    public void sendPaymentProcess() {
        if (payment != null) 
            sqsTemplate.send(SQS_url, payment);
        else 
            System.out.println("No payment to process.");
        
    }
}
