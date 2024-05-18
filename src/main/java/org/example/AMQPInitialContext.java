package org.example;

import org.example.utils.BrokerConfig;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Properties;

public class AMQPInitialContext extends InitialContext {

    public AMQPInitialContext(final BrokerConfig config, final String host) throws NamingException {
        Properties properties = new Properties();
        properties.put(INITIAL_CONTEXT_FACTORY, "org.apache.qpid.jms.jndi.JmsInitialContextFactory");
        properties.put("connectionFactory.AMQPConnection", host);
        properties.put(SECURITY_PRINCIPAL, config.getLogin());
        properties.put(SECURITY_CREDENTIALS, config.getPassword());
        init(properties);
    }
}
