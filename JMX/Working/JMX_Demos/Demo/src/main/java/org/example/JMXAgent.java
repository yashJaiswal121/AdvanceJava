package org.example;

import java.lang.management.ManagementFactory;
import java.util.Scanner;
import javax.management.*;

public class JMXAgent {

    public MBeanServer getPlatformServer(){
        //Platform Server
        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
        return mbs;
    }

    public void registerMBean(String mbeanName) throws MalformedObjectNameException,
            ClassNotFoundException, InstantiationException, IllegalAccessException,
            NotCompliantMBeanException, InstanceAlreadyExistsException, MBeanRegistrationException {
        MBeanServer mbs = getPlatformServer();
        if(mbs != null){
            // Construct the ObjectName for the Hello MBean we will register
            String mBeanStr = "org.example:type="+mbeanName;
            String mBeanNameWithClasspath =  "org.example."+mbeanName;
            ObjectName mbeanObjName = new ObjectName(mBeanStr);
            MBean mbean = (MBean)(Class.forName(mBeanNameWithClasspath)).newInstance();
            mbs.registerMBean(mbean, mbeanObjName);
            //Scanner scanner = new Scanner(System.in);
        }



    }
}
