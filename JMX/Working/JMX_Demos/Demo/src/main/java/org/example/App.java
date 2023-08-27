package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.management.ObjectName;
import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * Hello world!
 *
 */
public class App 
{
    private static final Logger logger = LogManager.getLogger(App.class);

    public static void main( String[] args ) {
        JMXAgent agent = new JMXAgent();

        try{
            agent.registerMBean("LConfiguration");

        }catch (Exception e){
            System.out.println("Println Exception : "+e);
        }

        Scanner sc = new Scanner(System.in);
        sc.nextLine();
    }
}
