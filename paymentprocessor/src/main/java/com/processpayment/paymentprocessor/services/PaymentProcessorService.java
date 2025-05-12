package com.processpayment.paymentprocessor.services;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.processpayment.paymentprocessor.db.entities.CreditCard;
import com.processpayment.paymentprocessor.db.entities.ProcessPayment;
import com.processpayment.paymentprocessor.db.repositories.CreditCardRepository;
import com.processpayment.paymentprocessor.db.repositories.ProcessPaymentRepository;
import com.processpayment.paymentprocessor.dtos.PaymentProcessPayload;
import com.processpayment.paymentprocessor.dtos.PaymentStatusProcessed;
import com.processpayment.paymentprocessor.utils.PaymentStatus;

@Service
public class PaymentProcessorService {

    private final ProcessPaymentRepository processPaymentRepository;
    private final CreditCardRepository creditCardRepository;

    public PaymentProcessorService(ProcessPaymentRepository processPaymentRepository, CreditCardRepository creditCardRepository) {
        this.processPaymentRepository = processPaymentRepository;
        this.creditCardRepository = creditCardRepository;
    }

    public PaymentStatusProcessed processing(PaymentProcessPayload paymentProcessPayload) {
        UUID id = saveProcessPayment(paymentProcessPayload);
        PaymentStatusProcessed status = checkCreditCardInformations(paymentProcessPayload, id);
        return status;
    }

    private UUID saveProcessPayment(PaymentProcessPayload paymentProcessPayload) {
        ProcessPayment payment = new ProcessPayment();
        payment.setPaymentid(paymentProcessPayload.paymentid());
        payment.setCardnumber(paymentProcessPayload.cardnumber());
        payment.setCvv(paymentProcessPayload.cvv());
        payment.setAmount(paymentProcessPayload.amount());
        payment.setExpireddate(LocalDateTime.parse(paymentProcessPayload.expireddate()));
        payment.setStatuspayment(PaymentStatus.valueOf(paymentProcessPayload.statuspayment()));
        processPaymentRepository.save(payment);
        return payment.getId();
    }

    private PaymentStatusProcessed checkCreditCardInformations(PaymentProcessPayload paymentProcessPayload, UUID id) {
        String paymentid = paymentProcessPayload.paymentid();
        Optional<CreditCard> card = this.creditCardRepository.findByCardnumberAndCvvAndExpireddate(paymentProcessPayload.cardnumber(), paymentProcessPayload.cvv(), LocalDateTime.parse(paymentProcessPayload.expireddate()));
        ProcessPayment payment = this.processPaymentRepository.findById(id).get();
        if(card.isEmpty()){
            payment.setStatuspayment(PaymentStatus.REJECTED);
            processPaymentRepository.save(payment);
            return new PaymentStatusProcessed(paymentid, PaymentStatus.REJECTED.toString());
        }
        CreditCard creditCard = card.get();
        Float limit = creditCard.getActuallimit();
        if(limit >= paymentProcessPayload.amount()){
            creditCard.setActuallimit(limit - paymentProcessPayload.amount());
            creditCardRepository.save(creditCard);
            payment.setStatuspayment(PaymentStatus.APPROVED);
            processPaymentRepository.save(payment);
            return new PaymentStatusProcessed(paymentid, PaymentStatus.APPROVED.toString());
        }
        payment.setStatuspayment(PaymentStatus.REJECTED);
        processPaymentRepository.save(payment);
        return new PaymentStatusProcessed(paymentid, PaymentStatus.REJECTED.toString());
    }
}
