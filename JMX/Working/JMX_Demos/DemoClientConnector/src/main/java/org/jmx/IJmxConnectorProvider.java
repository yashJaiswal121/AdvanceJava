package org.jmx;

public interface IJmxConnectorProvider {
    JmxConnectorClient getConnectorClient(String type);

    void setUrl(String url);

    void setUser(String user);

    void setPasswd(String passwd);
}
