package com.reflectionDemos;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

public class Reflection3 {

    /*Example to Create a WebServer and Load its ServerConfiguration via Reflection.
    * ServerConfiguration will be a Singleton Class, will have private constructor, which will be accessed via Reflection
    * */
    public static void main(String[] args) throws IOException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        WebServer webServer = new WebServer(8082);
        webServer.startServer();
    }

}

class WebServer{

    private HttpServer httpServer;

    public WebServer(Integer port) throws IOException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        if(httpServer != null)
            throw new IOException("Server has already started!");
        this.initConfiguration(port);
        httpServer = HttpServer.create(ServerConfiguration.getInstance().getAddress(),0);
    }

    public void startServer() {

        httpServer.createContext("/greeting").setHandler(exchange -> {
            String responseMsg = ServerConfiguration.getInstance().getStartupMsg();
            exchange.sendResponseHeaders(200,responseMsg.length());
            exchange.getResponseBody().write(responseMsg.getBytes(StandardCharsets.UTF_8));
            exchange.getResponseBody().flush();
            exchange.getResponseBody().close();
        });

        System.out.println("Starting Server on localhost");
        httpServer.start();
    }

    private ServerConfiguration initConfiguration(Integer port) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Constructor<?> constructor = ServerConfiguration.class.getDeclaredConstructor(port.getClass());//Imp
        constructor.setAccessible(true); //Imp
        return (ServerConfiguration)constructor.newInstance(port);//Imp
    }

}


class ServerConfiguration{
    private static ServerConfiguration serverConfigurationInstance = null;
    private InetSocketAddress address;
    private String startupMsg;

    private ServerConfiguration(Integer port){
        startupMsg = "Hello on Rest Client";
        address = new InetSocketAddress("localhost", port);

        if(serverConfigurationInstance == null)
            serverConfigurationInstance = this;
    }

    public static ServerConfiguration getInstance(){
        return serverConfigurationInstance;
    }

    public InetSocketAddress getAddress() {
        return address;
    }

    public String getStartupMsg() {
        return startupMsg;
    }
}
