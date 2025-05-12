package com.processpayment.paymentprocessor.db.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import com.processpayment.paymentprocessor.utils.PaymentStatus;

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
@Table(name = "process-payment")
public class ProcessPayment {
    @Id
    @GeneratedValue(strategy=GenerationType.UUID)
    private UUID id;

    private String paymentid;

    private Float amount;

    private String cardnumber;

    private String cvv;

    private LocalDateTime expireddate;

    private LocalDateTime createdate;

    @Enumerated(EnumType.STRING)
    private PaymentStatus statuspayment;

    public ProcessPayment() {
        this.createdate = LocalDateTime.now();
    }

    public String getExpiredate() {
        System.err.println(this.expireddate.toString());
        return this.expireddate.toString();
    }

    public String getCreatedate() {
        return this.createdate.toString();
    }
}
