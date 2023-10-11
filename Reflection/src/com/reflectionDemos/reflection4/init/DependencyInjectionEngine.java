package com.reflectionDemos.reflection4.init;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class DependencyInjectionEngine {

    public static <T> T createObjectRecursively(Class<T> clazz) throws InvocationTargetException, InstantiationException, IllegalAccessException {
        //TODO: Advance This Via Topological Sort, Currently assume DAG
        /*
        * Creating Any Object Using 1st Constructor only.
        * Begin from Class clazz, get its Dependencies, then recursively get their Dependencies,and so on.
        * */

        System.out.println("Creating Object of = " +clazz.getSimpleName());

        Constructor<?>[] constructors = clazz.getDeclaredConstructors();
        Constructor<?> firstConstructor = constructors[0];

        Class<?>[] dependenciesClazz = firstConstructor.getParameterTypes();
        List<Object> dependenciesObject = new ArrayList<>();

        for(Class<?> dependency : dependenciesClazz){
            dependenciesObject.add(createObjectRecursively(dependency));
        }
        firstConstructor.setAccessible(true); //Importatnt to access and create object of Package-Private Classes
        return (T) firstConstructor.newInstance(dependenciesObject.toArray()); //Here newInstance(args) , args-> are actual param Objects and not param Class<?> types
    }
}
