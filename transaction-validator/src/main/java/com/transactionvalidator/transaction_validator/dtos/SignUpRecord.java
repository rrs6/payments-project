package com.transactionvalidator.transaction_validator.dtos;

import java.util.List;

public record SignUpRecord(String email, String password, List<String> roles) {

}
