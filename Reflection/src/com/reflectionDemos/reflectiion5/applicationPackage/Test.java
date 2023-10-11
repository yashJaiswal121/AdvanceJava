package com.reflectionDemos.reflectiion5.applicationPackage;

public class Test {

    public Integer id;
    private String name;
    private int age;  //field.setAccessible(true);
    protected float weight;

    double height; //Package Private ..field.setAccessible(true);

    public Test(Integer id, String name, int age, float weight, double height) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.height = height;
    }
}
