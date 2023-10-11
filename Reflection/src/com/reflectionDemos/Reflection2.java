package com.reflectionDemos;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Reflection2 {
    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        printConstructorData(Address.class);
        System.out.println("==================GENERAL========================");
        printConstructorData(Person.class);

        System.out.println("==================REFLECTION-FACTORY-DEMO========================");
        Address address = (Address) factoryMethod(Address.class,"282010","Agra");
        System.out.println("Address = " + address);

        Person person1 = (Person) factoryMethod(Person.class,"TestUser",20,address);
        System.out.println("Person1 = " + person1);

        Person person2 =  factoryMethodWithGeneric(Person.class);
        System.out.println("Person2 = " + person2);

    }

    public static <T> T factoryMethodWithGeneric(Class<T> clazz,Object... args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        List<Class<?>> paramTypes = Arrays.stream(args).map(arg -> arg.getClass()).collect(Collectors.toList());
        //Imp-> paramTypes.toArray(new Class[0]) Converts List to ...(var-args)
        Constructor<?> specifiedConstructor = clazz.getDeclaredConstructor(paramTypes.toArray(new Class[0]));
        return (T)specifiedConstructor.newInstance(args);
    }


    public static Object factoryMethod(Class<?> clazz,Object... args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        List<Class<?>> paramTypes = Arrays.stream(args).map(arg -> arg.getClass()).collect(Collectors.toList());

        //Imp-> paramTypes.toArray(new Class[0]) Converts List to ...(var-args)
        Constructor<?> specifiedConstructor = clazz.getDeclaredConstructor(paramTypes.toArray(new Class[0]));
        return specifiedConstructor.newInstance(args);
    }

    public static void printConstructorData(Class<?> clazz){
        Constructor<?>[] constructors = clazz.getDeclaredConstructors();
        System.out.println("Class " + clazz.getSimpleName() +" has " + constructors.length +" constructors. Printing Details:" );

        for(Constructor<?> constructor : constructors){
            Class<?>[] params = constructor.getParameterTypes();

            List<String> paramTypeNames = Arrays.asList(params).stream()
                    .map(param -> param.getSimpleName()).collect(Collectors.toList());

            System.out.println(paramTypeNames);
        }
    }


    static class Person{
        private String name;
        private Integer age;
        private Address address;

        public Person() {
            this.name = "Default Name";
            this.age = -1;
            this.address = new Address();
        }

        public Person(String name, Integer age,Address address) {
            this.name = name;
            this.age = age;
            this.address = address;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    ", address=" + address +
                    '}';
        }
    }

    static class Address{
        private String zipcode;
        private String city;

        public Address() {
            this.zipcode = "Default Zipcode";
            this.city = "Default City";
        }

        public Address(String zipcode, String city) {
            this.zipcode = zipcode;
            this.city = city;
        }

        @Override
        public String toString() {
            return "Address{" +
                    "zipcode='" + zipcode + '\'' +
                    ", city='" + city + '\'' +
                    '}';
        }
    }


}
