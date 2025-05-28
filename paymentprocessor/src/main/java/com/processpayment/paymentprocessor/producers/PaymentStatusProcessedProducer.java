package com.processpayment.paymentprocessor.producers;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.processpayment.paymentprocessor.dtos.PaymentStatusProcessed;
import com.processpayment.paymentprocessor.utils.mapper.WriteClassAsString;

@Component
public class PaymentStatusProcessedProducer {
    private final RabbitTemplate rabbitTemplate;

    @Value("${rabbitmq.producer.routingkey}")
    private String routingKey;

    @Value("${rabbitmq.producer.exchangename}")
    private String exchangeName;


    public PaymentStatusProcessedProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendProcessedPayment(PaymentStatusProcessed paymentStatusProcessed) throws JsonProcessingException {
        rabbitTemplate.convertAndSend(exchangeName, routingKey, paymentStatusProcessed);
    }
}
