package com.processpayment.paymentprocessor.producers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.processpayment.paymentprocessor.dtos.PaymentStatusProcessed;

import io.awspring.cloud.sqs.operations.SqsTemplate;

@Component
public class PaymentStatusProcessedProducer {
    private final SqsTemplate sqsTemplate;
    private final ObjectMapper mapper;
    @Value("${aws.sqs.url}")
    private String SQS_url;

    public PaymentStatusProcessedProducer(SqsTemplate sqsTemplate) {
        this.sqsTemplate = sqsTemplate;
        this.mapper = new ObjectMapper();
    }

    public void sendProcessedPayment(PaymentStatusProcessed paymentStatusProcessed) throws JsonProcessingException {
        String body = mapper.writeValueAsString(paymentStatusProcessed);
        sqsTemplate.send(SQS_url, body);
    }
}
