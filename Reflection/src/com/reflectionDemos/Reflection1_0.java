package com.reflectionDemos;

import java.util.HashMap;
import java.util.Map;

public class Reflection1_0 {

    public static void main(String[] args) throws ClassNotFoundException {

        Class<String> stringClass = String.class;

        Map<String,Integer> map = new HashMap<>();
        Class<?> hashMapClass = map.getClass();

        System.out.println(hashMapClass.getTypeName());

        Class<?> squareClass = Class.forName("com.reflectionDemos.Reflection1_0$Square");

        printClassInfo(stringClass,hashMapClass,squareClass);

        //Anonymous Class
        Drawable circleObj = new Drawable() {
            @Override
            public int getNumCorners() {
                return 0;
            }
        };

        //printClassInfo(Collection.class,boolean.class,int[][].class,Color.class,circleObj.getClass());
    }

    private static void printClassInfo(Class<?>... classes){

        for(Class<?> clazz : classes){
            System.out.println(String.format("class name : %s , class package name %s" , clazz.getSimpleName(),clazz.getPackageName()));

            Class<?>[] implementingInterfaces = clazz.getInterfaces();
            for (Class<?> implementedIface : implementingInterfaces){
                System.out.println("It implements Iface : " + implementedIface.getSimpleName());
            }

            System.out.println("Is Array : " + clazz.isArray());
            System.out.println("Is Primitive : " + clazz.isPrimitive());
            System.out.println("Is Enum : " + clazz.isEnum());
            System.out.println("Is Interface : " + clazz.isInterface());
            System.out.println("Is Anonymous class : " + clazz.isAnonymousClass());

            System.out.println("===========================");
        }
    }

    private static class Square implements Drawable{

        @Override
        public int getNumCorners() {
            return 4;
        }
    }

    private static interface Drawable{
        int getNumCorners();
    }

    private enum Color{
        RED,
        GREEN,
        BLUE;
    }

}

