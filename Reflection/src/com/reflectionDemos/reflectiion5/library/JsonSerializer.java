package com.reflectionDemos.reflectiion5.library;

import java.lang.reflect.Field;

public class JsonSerializer {

    public <T>String serialize(T instance) throws IllegalAccessException {
        StringBuilder serializedStr = new StringBuilder(" { ");
        Field[] fields = instance.getClass().getDeclaredFields();
        int idx=1;
        for (Field field :fields){
            if(field.isSynthetic()){
               continue;
            }

            field.setAccessible(true); //Same as Constructor, restricted Fields are made accessable
            serializedStr.append(field.getName()).append(" : ");
            serializedStr.append(getFormattedValue(field,instance));

            if(idx < fields.length){
                serializedStr.append(", ");
            }

            idx++;
        }

        serializedStr.append(" } ");


        return serializedStr.toString();
    }

    private <T>String getFormattedValue(Field field,T instance) throws IllegalAccessException {
        if(field.getType() == Float.class || field.getType() == float.class || field.getType() == double.class || field.getType() == Double.class ){
            return String.format("%2f",field.get(instance));
        }
        return field.get(instance).toString();
    }


}
