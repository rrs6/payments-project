package com.transactionvalidator.transaction_validator.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.transactionvalidator.transaction_validator.db.entities.Payment;
import com.transactionvalidator.transaction_validator.db.repositories.PaymentRepository;
import com.transactionvalidator.transaction_validator.producers.CreatePaymentProcess;

@Service
public class PaymentService {

    @Autowired
    private CreatePaymentProcess createPaymentProcess;

    @Autowired
    private PaymentRepository paymentRepository;

    public List<Payment> getAllUsers() {
        return paymentRepository.findAll();
    }

    public Payment save(Payment user) {
        Payment payment = paymentRepository.save(user);
        createPaymentProcess.setPayload(payment);
        createPaymentProcess.sendPaymentProcess();
        return payment;
    }
}