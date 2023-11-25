package com.micro.training.msagreement.integration;

import com.micro.training.msagreement.integration.models.MessageObj;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailNotificationClient {
    private final RabbitTemplate rabbitTemplate;

    public void sendEmailNotfication(MessageObj messageObjParam) {
        rabbitTemplate.convertAndSend("message-t-exchange",
                                      "send.email.eu.turkey.istanbul",
                                      messageObjParam);
    }

}
