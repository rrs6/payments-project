package com.processpayment.paymentprocessor.db.repositories;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.processpayment.paymentprocessor.db.entities.CreditCard;




public interface CreditCardRepository extends JpaRepository<CreditCard, UUID> {
    public Optional<CreditCard> findByCardnumberAndCvvAndExpireddate(String cardnumber, String cvv, LocalDateTime expireddate);
}
