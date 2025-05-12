package com.processpayment.paymentprocessor.db.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Entity;
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
@Table(name = "creditcard")
public class CreditCard {
    @Id
    @GeneratedValue(strategy=GenerationType.UUID)
    private UUID id;

    private String cardnumber;

    private String cvv;

    private LocalDateTime expireddate;

    private LocalDateTime createdate;

    private Float totallimit;

    private Float actuallimit;

    public CreditCard() {
        this.createdate = LocalDateTime.now();
    }
}
