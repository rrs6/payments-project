package com.transactionvalidator.transaction_validator.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.transactionvalidator.transaction_validator.dtos.SignInRecord;
import com.transactionvalidator.transaction_validator.dtos.SignUpRecord;
import com.transactionvalidator.transaction_validator.services.UserService;

@RestController
@RequestMapping("/auth")
public class Authentication {
    private UserService userService;

    public Authentication(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<Void> authenticateUser(@RequestBody SignUpRecord signUp) {
        userService.createUser(signUp);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/signin")
    public ResponseEntity<String> signIn(@RequestBody SignInRecord signIn) {
        String token = userService.authenticateUser(signIn);
        return new ResponseEntity<String>(token, HttpStatus.OK);
    }
    
}
