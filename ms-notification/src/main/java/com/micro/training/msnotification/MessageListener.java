package com.micro.training.msnotification;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

@Component
public class MessageListener {

    @RabbitListener(bindings = @QueueBinding(value = @Queue(name = "sms-queue",
            autoDelete = "false",
            durable = "true"),
            exchange = @Exchange(name = "message-exchange",
                    autoDelete = "false",
                    durable = "true",
                    type = ExchangeTypes.DIRECT),
            key = "send.sms"))
    public void handleSMSMessage(MessageObj message){
        System.out.println("SMS Message : " + message);
    }

    @RabbitListener(bindings = @QueueBinding(value = @Queue(name = "email-queue",
            autoDelete = "false",
            durable = "true"),
            exchange = @Exchange(name = "message-exchange",
                    autoDelete = "false",
                    durable = "true",
                    type = ExchangeTypes.DIRECT),
            key = "send.email"))
    public void handleEmailMessage(MessageObj message){
        System.out.println("EMAIL Message : " + message);
    }

    // send.email.eu.turkey.istanbul -> Message -> email-t-queue, all-message-t-queue
    // send.sms.eu.turkey.istanbul -> Message -> all-message-t-queue, sms-t-queue
    @RabbitListener(bindings = @QueueBinding(value = @Queue(name = "email-t-queue",
            autoDelete = "false",
            durable = "true"),
            exchange = @Exchange(name = "message-t-exchange",
                    autoDelete = "false",
                    durable = "true",
                    type = ExchangeTypes.TOPIC),
            key = "send.email.#"))
    @SendTo("message-response-exchange/email.response")
    public String handleEmailMessageTopic(MessageObj message){
        System.out.println("EMAIL Topic Message : " + message);
        return "EMAIL sent : " + message;
    }


    @RabbitListener(bindings = @QueueBinding(value = @Queue(name = "all-message-t-queue",
            autoDelete = "false",
            durable = "true"),
            exchange = @Exchange(name = "message-t-exchange",
                    autoDelete = "false",
                    durable = "true",
                    type = ExchangeTypes.TOPIC),
            key = "send.#"))
    public void handleEmailMessageTopicAll(MessageObj message){
        System.out.println("All Message Topic : " + message);
    }

}
