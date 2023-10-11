package com.reflectionDemos.reflectiion5.applicationPackage;

import com.reflectionDemos.reflectiion5.library.JsonSerializer;

public class Client {
    public static void main(String[] args) {
        Test test = new Test(1,"ABC",12,30.4534f,135.546);
        JsonSerializer serializer = new JsonSerializer();
        try {
            System.out.println(serializer.serialize(test));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
