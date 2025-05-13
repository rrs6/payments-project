package com.processpayment.paymentprocessor.producers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.processpayment.paymentprocessor.dtos.PaymentStatusProcessed;
import com.processpayment.paymentprocessor.utils.mapper.WriteClassAsString;

import io.awspring.cloud.sqs.operations.SqsTemplate;

@Component
public class PaymentStatusProcessedProducer {
    private final SqsTemplate sqsTemplate;
    private final WriteClassAsString mapper;
    @Value("${aws.sqs.url}")
    private String SQS_url;

    public PaymentStatusProcessedProducer(SqsTemplate sqsTemplate, WriteClassAsString mapper) {
        this.sqsTemplate = sqsTemplate;
        this.mapper = mapper;
    }

    public void sendProcessedPayment(PaymentStatusProcessed paymentStatusProcessed) throws JsonProcessingException {
        String body = mapper.write(paymentStatusProcessed);
        sqsTemplate.send(SQS_url, body);
    }
}
