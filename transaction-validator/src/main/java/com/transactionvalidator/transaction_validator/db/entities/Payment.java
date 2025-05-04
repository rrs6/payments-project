package com.transactionvalidator.transaction_validator.db.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import com.transactionvalidator.transaction_validator.utils.enums.PaymentStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Entity
@Table(name = "payments")
public class Payment {
    @Id
    @GeneratedValue(strategy=GenerationType.UUID)
    private UUID id;

    private String cardnumber;

    private String cvv;

    private Float amount;

    private LocalDateTime expiredat;

    private LocalDateTime createdat;

    @Enumerated(EnumType.STRING)
    private PaymentStatus statuspayment;

    public Payment() {
        this.createdat = LocalDateTime.now();
        this.statuspayment = PaymentStatus.PENDING;
    }
}
