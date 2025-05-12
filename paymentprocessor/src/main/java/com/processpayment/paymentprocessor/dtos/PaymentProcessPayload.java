package com.processpayment.paymentprocessor.dtos;

public record PaymentProcessPayload(String id, String paymentid, String cardnumber, String cvv, Float amount, String expireddate, String createdate, String statuspayment) {}