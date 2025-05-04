package com.transactionvalidator.transaction_validator.controllers;

import java.time.ZoneId;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.transactionvalidator.transaction_validator.db.entities.Payment;
import com.transactionvalidator.transaction_validator.dtos.PaymentProcessPayload;
import com.transactionvalidator.transaction_validator.services.PaymentService;

@RestController
@RequestMapping("/api/payments")
public class Payments {
    @Autowired
    public PaymentService paymentService;

    @PostMapping
    public String newPaymentProcess(@RequestBody PaymentProcessPayload paymentProcessPayload) {
        Payment payment = new Payment();
        payment.setCardnumber(paymentProcessPayload.cardNumber());
        payment.setCvv(paymentProcessPayload.cvv());
        payment.setAmount(paymentProcessPayload.amount());
        payment.setExpiredat(paymentProcessPayload.expiredAt().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
        Payment p = paymentService.save(payment);


        return p.getId().toString();
    }
}
