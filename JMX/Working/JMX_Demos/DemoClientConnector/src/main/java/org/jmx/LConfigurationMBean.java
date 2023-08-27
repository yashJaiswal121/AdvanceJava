package org.jmx;

public interface LConfigurationMBean extends MBean{
    void setLoggerProperty(String loggerProperty); //Attributes
    String getLoggerProperty();
    void refreshLoggerProperties(); //Operation
}
