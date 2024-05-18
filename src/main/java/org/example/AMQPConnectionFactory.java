package org.example;

import org.apache.qpid.jms.JmsConnectionFactory;
import org.example.utils.BrokerConfig;

import java.util.UUID;

public class AMQPConnectionFactory {

    private final String uuid = UUID.randomUUID().toString();
    private final String login;
    private final String password;
    private final JmsConnectionFactory connectionFactory;

    public AMQPConnectionFactory(final BrokerConfig config, final String host) {
        final AMQPInitialContext context =
    }
}
