package com.processpayment.paymentprocessor.db.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.processpayment.paymentprocessor.db.entities.ProcessPayment;

@Repository
public interface ProcessPaymentRepository extends JpaRepository<ProcessPayment, UUID> {
    @Override
    public Optional<ProcessPayment> findById(UUID id);
}
