package com.transactionvalidator.transaction_validator.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.transactionvalidator.transaction_validator.db.entities.Payment;
import com.transactionvalidator.transaction_validator.db.repositories.PaymentRepository;
import com.transactionvalidator.transaction_validator.dtos.PaymentProcessingRecord;
import com.transactionvalidator.transaction_validator.dtos.ValidatorResponse;
import com.transactionvalidator.transaction_validator.producers.CreatePaymentProcess;
import com.transactionvalidator.transaction_validator.utils.enums.PaymentStatus;

@Service
public class PaymentService {
    @Autowired
    private CreatePaymentProcess createPaymentProcess;

    @Autowired
    private PaymentRepository paymentRepository;

    public Payment save(Payment payment) {
        Payment paymentt = paymentRepository.save(payment);
        PaymentProcessingRecord paymentToSend = new PaymentProcessingRecord(
        paymentt.getId().toString(),
        paymentt.getId().toString(),
        paymentt.getCardnumber(),
        paymentt.getCvv(),
        paymentt.getAmount(),
        paymentt.getExpiredate(),
        paymentt.getCreatedate(),
        paymentt.getStatuspayment().toString());
        try {
            createPaymentProcess.sendPaymentProcess(paymentToSend);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return payment;
    }

    public void updatePaymentByValidatorResponse(ValidatorResponse status) {
        Payment payment = paymentRepository.findById(UUID.fromString(status.paymentid())).get();
        payment.setStatuspayment(PaymentStatus.valueOf(status.status()));
        paymentRepository.save(payment);
    }
}