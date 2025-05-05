package com.transactionvalidator.transaction_validator.producers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.transactionvalidator.transaction_validator.dtos.PaymentRecord;

import io.awspring.cloud.sqs.operations.SqsTemplate;

@Component
public class CreatePaymentProcess {
    private PaymentRecord payment;

    @Autowired
    private SqsTemplate sqsTemplate;
    private final String SQS_url = "https://localhost.localstack.cloud:4566/000000000000/payment-process";

    public void setPayload(PaymentRecord payment) {
        this.payment = payment;
    }

    public void sendPaymentProcess() throws JsonProcessingException {
        if (payment != null){
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(payment);
            sqsTemplate.send(SQS_url, json);
        } else {
            System.out.println("No payment to process.");
        }
        
    }
}
