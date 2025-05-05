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

    private LocalDateTime expireddate;

    private LocalDateTime createdate;

    @Enumerated(EnumType.STRING)
    private PaymentStatus statuspayment;

    public Payment() {
        this.createdate = LocalDateTime.now();
        this.statuspayment = PaymentStatus.PENDING;
    }

    public String getExpiredate() {
        System.err.println(this.expireddate.toString());
        return this.expireddate.toString();
    }

    public String getCreatedate() {
        return this.createdate.toString();
    }
}
