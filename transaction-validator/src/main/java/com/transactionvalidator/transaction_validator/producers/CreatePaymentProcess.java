package com.transactionvalidator.transaction_validator.producers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.transactionvalidator.transaction_validator.dtos.PaymentProcessingRecord;

import io.awspring.cloud.sqs.operations.SqsTemplate;

@Component
public class CreatePaymentProcess {
    private final SqsTemplate sqsTemplate;
    @Value("${aws.sqs.url}")
    private String sqsUrl;
    private final ObjectMapper mapper;


    public CreatePaymentProcess(SqsTemplate sqsTemplate) {
        this.sqsTemplate = sqsTemplate;
        this.mapper = new ObjectMapper();
    }

    public void sendPaymentProcess(PaymentProcessingRecord payment) throws JsonProcessingException {
        String body = mapper.writeValueAsString(payment);
        sqsTemplate.send(this.sqsUrl, body);
    }
}
