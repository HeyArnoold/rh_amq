package org.example.service;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;
import javax.jms.Message;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@EnableAsync
@Service
public class ReceiverService {
    @Autowired
    DispatcherService dispatcherService;
    @Autowired
    LoggingService loggingService;

    @Value("${jms.queue.Example.write}")
    String respQueue;

    @Async
    @JmsListener(destination = "${jms.queue.Example.read}")
    public void listenMessage(Message message) throws JMSException, InterruptedException, IOException {

        String correlationId = message.getJMSCorrelationID();


        loggingService.log("Получили сообщение с corrId " + correlationId);
        loggingService.log("\n" + message.getBody(String.class));

        Thread.sleep(1000);

        String respBody = IOUtils.toString(ReceiverService.class.getResource("/templates/testTemplate.xml"), StandardCharsets.UTF_8);

        dispatcherService.sendMessage(respBody, correlationId, respQueue);

        loggingService.log("Отправили сообщение с corrId " + correlationId);
        loggingService.log("\n" + respBody);
    }
}
