package com.processpayment.paymentprocessor.utils.mapper;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class WriteClassAsString {
    private static final ObjectMapper mapper = new ObjectMapper();

    public String write(Object object) throws JsonProcessingException {
        return WriteClassAsString.mapper.writeValueAsString(object);
    }
}

