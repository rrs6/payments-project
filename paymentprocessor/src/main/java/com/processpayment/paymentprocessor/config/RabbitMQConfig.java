package com.processpayment.paymentprocessor.config;

import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Declarables;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    @Value("${rabbitmq.consumer.queuename}")
    private String consumerQueueName;

    @Value("${rabbitmq.consumer.exchangename}")
    private String consumerExchangeName;

    @Value("${rabbitmq.consumer.routingkey}")
    private String consumerRountingKey;

    @Value("${rabbitmq.producer.queuename}")
    private String producerQueueName;

    @Value("${rabbitmq.producer.exchangename}")
    private String producerExchangeName;

    @Value("${rabbitmq.producer.routingkey}")
    private String producerRountingKey;


    @Bean
    public Declarables config() {
        Queue consumerQueue = new Queue(consumerQueueName, true);
        DirectExchange consumerExchange = new DirectExchange(consumerExchangeName);

        Queue producerQueue = new Queue(producerQueueName, true);
        DirectExchange producerExchange = new DirectExchange(producerExchangeName);
        return new Declarables(
            consumerQueue,
            consumerExchange,
            BindingBuilder.bind(consumerQueue).to(consumerExchange).with(consumerRountingKey),
            producerQueue,
            producerExchange,
            BindingBuilder.bind(producerQueue).to(producerExchange).with(producerRountingKey)
        );
    }
    
    @Bean
    public Jackson2JsonMessageConverter jackson2JsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

}
