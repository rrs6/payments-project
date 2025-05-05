package com.transactionvalidator.transaction_validator.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.transactionvalidator.transaction_validator.db.entities.Payment;
import com.transactionvalidator.transaction_validator.db.repositories.PaymentRepository;
import com.transactionvalidator.transaction_validator.dtos.PaymentRecord;
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

    public Payment save(Payment payment) {
        Payment paymentt = paymentRepository.save(payment);
        createPaymentProcess.setPayload(new PaymentRecord(paymentt.getId().toString(),
        paymentt.getCardnumber(),
        paymentt.getCvv(),
        paymentt.getAmount(),
        paymentt.getExpiredate(),
        paymentt.getCreatedate(),
        paymentt.getStatuspayment().toString()));
        try {
            createPaymentProcess.sendPaymentProcess();
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return payment;
    }
}