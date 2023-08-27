package org.jmx;

import javax.management.JMX;
import javax.management.MBeanServerConnection;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class RmiJmxConnectorClient<T> implements JmxConnectorClient<T>{

    private String serviceUrl;
    private String user;
    private String passwd;
    private MBeanServerConnection mbsc;

    @Override
    public void init(String serviceUrl, String user, String passwd) {
        this.serviceUrl = serviceUrl;
        this.user = user;
        this.passwd = passwd;
    }

    @Override
    public void connect() {
        try {
                JMXServiceURL jmxServiceUrl = new JMXServiceURL(serviceUrl);
                Map<String, ?> env = user == null ? null : Map.of(JMXConnector.CREDENTIALS, new String[] {user, passwd});
                JMXConnector jmxc = JMXConnectorFactory.connect(jmxServiceUrl, env);
                mbsc = jmxc.getMBeanServerConnection();

        } catch (IOException e) {
            throw new RuntimeException("Can not create client for remote JMX " + serviceUrl, e);
        }
    }

    @Override
    public T getProxyMBean(String objectName, Class clazz){
        try{
            ObjectName mbeanName = new ObjectName(objectName);
            return (T)(JMX.newMBeanProxy(mbsc, mbeanName, clazz, true));
        }catch (MalformedObjectNameException e){
            throw new RuntimeException("Can not create ProxyMBean for " + objectName, e);
        }
    }
}
