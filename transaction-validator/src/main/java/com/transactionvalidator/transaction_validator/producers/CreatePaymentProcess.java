package com.transactionvalidator.transaction_validator.producers;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.transactionvalidator.transaction_validator.dtos.PaymentProcessingRecord;

@Component
public class CreatePaymentProcess {
    private final RabbitTemplate rabbitTemplate;

    @Value("${rabbitmq.producer.routingkey}")
    private String routingKey;

    @Value("${rabbitmq.producer.exchangename}")
    private String exchangeName;

    public CreatePaymentProcess(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendPaymentProcess(PaymentProcessingRecord payment) throws JsonProcessingException {
        rabbitTemplate.convertAndSend(exchangeName, routingKey, payment);
    }
}
