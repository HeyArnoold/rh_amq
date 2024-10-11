package org.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class DispatcherService {

    @Autowired
    JmsTemplate jmsTemplate;

    public void sendMessage(String messageBody, String correlationId, String jmsQueue) {
        jmsTemplate.convertAndSend(jmsQueue, messageBody, message -> {
            message.setJMSCorrelationID(correlationId);
            return message;
        });
    }
}
