package com.reflectionDemos;

public class Test {
    public static void main(String[] args) {
        Child o1 = new Child();
        Parent o2 = new Child();

        o1.B();
        o2.B();
    }
}

class Parent{

    void A(){
        System.out.println("A called from Parent");
    }

    void B(){
        this.A();
        System.out.println("B called from Parent");
    }

}

class Child extends Parent{

    void A(){
        System.out.println("A called from Child");
    }

}
