package org.example;

import org.apache.logging.log4j.Level;

public interface LConfigurationMBean extends MBean{
    void setLoggerProperty(String loggerProperty); //Attributes
    String getLoggerProperty();
    void refreshLoggerProperties(); //Operation
}
