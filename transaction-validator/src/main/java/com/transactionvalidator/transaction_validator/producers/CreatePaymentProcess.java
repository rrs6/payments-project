package com.transactionvalidator.transaction_validator.producers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.transactionvalidator.transaction_validator.dtos.PaymentProcessingRecord;
import com.transactionvalidator.transaction_validator.utils.mapper.WriteClassAsString;

import io.awspring.cloud.sqs.operations.SqsTemplate;

@Component
public class CreatePaymentProcess {
    private final SqsTemplate sqsTemplate;
    private final WriteClassAsString mapper;
    @Value("${aws.sqs.url}")
    private String sqsUrl;

    public CreatePaymentProcess(SqsTemplate sqsTemplate, WriteClassAsString mapper) {
        this.sqsTemplate = sqsTemplate;
        this.mapper = mapper;
    }

    public void sendPaymentProcess(PaymentProcessingRecord payment) throws JsonProcessingException {
        String body = mapper.write(payment);
        sqsTemplate.send(this.sqsUrl, body);
    }
}
