package com.transactionvalidator.transaction_validator.consumers;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.transactionvalidator.transaction_validator.dtos.ValidatorResponse;
import com.transactionvalidator.transaction_validator.services.PaymentService;

@Component
public class ValidatorResponseConsumer {

    private final PaymentService paymentService;

    public ValidatorResponseConsumer(PaymentService paymentService) {
        this.paymentService = paymentService;
    }
    
    @RabbitListener(queues="${rabbitmq.consumer.queuename}")
    public void listen(ValidatorResponse response) {
        paymentService.updatePaymentByValidatorResponse(response);
    }
}
