package com.transactionvalidator.transaction_validator.dtos;

public record PaymentRecord(String id, String cardnumber, String cvv, Float amount, String expireddate, String createdate, String statuspayment) {}
