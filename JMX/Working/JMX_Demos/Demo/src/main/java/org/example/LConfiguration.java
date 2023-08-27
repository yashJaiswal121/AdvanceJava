package org.example;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.LoggerConfig;

public class LConfiguration implements LConfigurationMBean {

    private static final Logger logger = LogManager.getLogger(LConfiguration.class);
    //Attributes
    private String loggerProperty;

    @Override
    public void setLoggerProperty(String loggerProperty) {
        this.loggerProperty = loggerProperty;
    }

    @Override
    public String getLoggerProperty() {
        return loggerProperty;
    }

    //Operation
    @Override
    public void refreshLoggerProperties() {

        System.out.println("Started Refreshing Logger level set via log42j.xml as : "+logger.getLevel());

        LoggerContext context = (LoggerContext) LogManager.getContext(false);
        Configuration config = context.getConfiguration();
        LoggerConfig rootConfig = config.getLoggerConfig(LogManager.ROOT_LOGGER_NAME);
        rootConfig.setLevel(Level.getLevel(loggerProperty));

// You could also specify the actual logger name as below
// and it will return the LoggerConfig used by the Logger.
//        LoggerConfig loggerConfig = config.getLoggerConfig("com.apache.test");
//        loggerConfig.setLevel(Level.TRACE);

// This causes all Loggers to refetch information from their LoggerConfig.
        context.updateLoggers();

        System.out.println("Completed Refreshing Logger level!! New Logger level is : "+logger.getLevel());
        //throw new RuntimeException("Exception");
    }
}
