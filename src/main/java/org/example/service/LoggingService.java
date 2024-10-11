package org.example.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class LoggingService {

    Logger log = LoggerFactory.getLogger(LoggingService.class);

    public void log(String message) {
        log.info(message);
    }
}
