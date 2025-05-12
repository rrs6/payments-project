package com.transactionvalidator.transaction_validator.dtos;

public record PaymentProcessingRecord(String id, String paymentid, String cardnumber, String cvv, Float amount, String expireddate, String createdate, String statuspayment) {

}
