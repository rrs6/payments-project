package com.transactionvalidator.transaction_validator.db.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.transactionvalidator.transaction_validator.db.entities.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, UUID> {

}