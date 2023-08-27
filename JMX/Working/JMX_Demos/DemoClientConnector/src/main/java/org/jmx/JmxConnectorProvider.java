package org.jmx;

import java.util.concurrent.ConcurrentHashMap;

public class JmxConnectorProvider implements IJmxConnectorProvider{

    private String url;
    private String user;
    private String passwd;
    private ConcurrentHashMap<String,JmxConnectorClient> connectorClients = new ConcurrentHashMap<>();

    @Override
    public JmxConnectorClient getConnectorClient(String type) {
        final String key= this.url+"::"+type;
        JmxConnectorClient connectorClient = null;
        if(connectorClients.get(key) != null){
            connectorClients.get(key);
        }else{
            if(type.equalsIgnoreCase("rmi")){
                connectorClient = new RmiJmxConnectorClient<>();
                connectorClient.init(url,user,passwd);
                connectorClient.connect();
                connectorClients.put(key,connectorClient);
            }
        }

        return connectorClient;
    }

    @Override
    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public void setUser(String user) {
        this.user = user;
    }

    @Override
    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }
}
