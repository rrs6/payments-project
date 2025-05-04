package com.transactionvalidator.transaction_validator.dtos;

import java.util.Date;

public record PaymentProcessPayload(String cardNumber, String cvv, Float amount, Date expiredAt) {}
