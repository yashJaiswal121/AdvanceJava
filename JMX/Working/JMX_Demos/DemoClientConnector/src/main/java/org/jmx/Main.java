package org.jmx;

public class Main {
    public static void main(String[] args) {
        IJmxConnectorProvider connectorProvider = new JmxConnectorProvider();
        connectorProvider.setUrl("service:jmx:rmi:///jndi/rmi://:9999/jmxrmi");
//        connectorProvider.setPasswd("");
//        connectorProvider.setUser("");
        LConfigurationMBean proxyMBean =(LConfigurationMBean)
                (connectorProvider.getConnectorClient("RMI").getProxyMBean("org.example:type=LConfiguration",LConfigurationMBean.class));
        proxyMBean.setLoggerProperty("ERROR");
        proxyMBean.refreshLoggerProperties();
    }
}
