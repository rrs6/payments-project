package com.processpayment.paymentprocessor.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsAsyncClient;

@Configuration
public class SqsConfig {
    @Value("${aws.accessKeyId}")
    private String accessKey;

    @Value("${aws.secretAccessKey}")
    private String secretKey;

    @Bean
    public SqsAsyncClient amazonSQSClient() {
        return SqsAsyncClient.builder()
        .region(Region.US_EAST_1)
        .credentialsProvider(
            StaticCredentialsProvider.create(
            AwsBasicCredentials.create(this.accessKey, this.secretKey)
        )).build();
    }
}