package com.micro.training.msagreement.integration;

import com.micro.training.msagreement.integration.models.MessageObj;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class SMSSendListener {

    @RabbitListener(bindings = @QueueBinding(value = @Queue(name = "sms-t-queue",
            autoDelete = "false",
            durable = "true"),
            exchange = @Exchange(name = "message-t-exchange",
                    autoDelete = "false",
                    durable = "true",
                    type = ExchangeTypes.TOPIC),
            key = "send.sms.#"))
    public void handleSMSMessageTopic(MessageObj message){
        System.out.println("SMS Topic Message : " + message);
    }

}
