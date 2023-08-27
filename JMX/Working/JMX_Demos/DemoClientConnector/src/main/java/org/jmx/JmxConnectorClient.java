package org.jmx;

public interface JmxConnectorClient<T> {
    void init(String serviceUrl,String user, String passwd);
    void connect();
    T getProxyMBean(String objectName, Class clazz);
}
