package org.example.service;

import lombok.extern.log4j.Log4j2;
import org.example.xml.XmlProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;
import javax.jms.Message;

import static org.example.repository.TemplateRepository.getStringFromTemplate;


@EnableAsync
@Service
@Log4j2
public class RequestProcessingService {
    @Autowired
    DispatcherService dispatcherService;

    @Value("${jms.queue.write}")
    String respQueue;

    @Value("${template.name}")
    String templateName;

    @Async
    @JmsListener(destination = "${jms.queue.read}")
    public void listenMessage(Message message) throws JMSException, InterruptedException {

        String correlationId = message.getJMSCorrelationID();


        log.info("Получили сообщение с corrId {}", correlationId);
        log.info("\n{}", message.getBody(String.class));

        Thread.sleep(1000);

        String respBody = XmlProcessor.processXml(getStringFromTemplate(templateName));

        dispatcherService.sendMessage(respBody, correlationId, respQueue);

        log.info("Отправили сообщение с corrId {}", correlationId);
        log.info("\n{}", respBody);
    }
}
