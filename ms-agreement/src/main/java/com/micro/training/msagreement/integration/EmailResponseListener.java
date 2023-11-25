package com.micro.training.msagreement.integration;

import com.micro.training.msagreement.integration.models.MessageObj;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class EmailResponseListener {

    @RabbitListener(bindings = @QueueBinding(value = @Queue(name = "email-response-queue",
            autoDelete = "false",
            durable = "true"),
            exchange = @Exchange(name = "message-response-exchange",
                    autoDelete = "false",
                    durable = "true",
                    type = ExchangeTypes.DIRECT),
            key = "email.response"))
    public void handleSMSMessageTopic(String message){
        System.out.println("Email response received : " + message);
    }

}
