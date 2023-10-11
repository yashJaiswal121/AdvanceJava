package com.reflectionDemos;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Reflection1_1 {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        Map<String,String> hmap = new HashMap<>();

        PopupTypeInfo popupTypeInfo1 = ClassAnalyzer.createPopupTypeInfoFromClass(hmap.getClass());
        System.out.println(popupTypeInfo1);

        PopupTypeInfo popupTypeInfo2 = ClassAnalyzer.createPopupTypeInfoFromClass(PopupTypeInfo.class);
        System.out.println(popupTypeInfo2);


    }
}

class ClassAnalyzer {
    private static final List JDK_PACKAGE_PREFIXES =
            Arrays.asList("com.sun.", "java", "javax", "jdk", "org.w3c", "org.xml");

    private static final Predicate packageChecker =
            packagePrefix ->  ClassAnalyzer.JDK_PACKAGE_PREFIXES.stream().anyMatch(packConsts -> ((String)packagePrefix).startsWith((String)packConsts));

    public static PopupTypeInfo createPopupTypeInfoFromClass(Class<?> inputClass) {
        PopupTypeInfo popupTypeInfo = new PopupTypeInfo();


        popupTypeInfo.setPrimitive(inputClass.isPrimitive())
                .setInterface(inputClass.isInterface())
                .setEnum(inputClass.isEnum())
                .setName(inputClass.getName())
                .setJdk(isJdkClass(inputClass))
                .addAllInheritedClassNames(getAllInheritedClassNames(inputClass));

        return popupTypeInfo;
    }

    /*********** Helper Methods ***************/

    public static boolean isJdkClass(Class<?> inputClass) {
        boolean isInternalClass = false || inputClass.isPrimitive();
        String packageName = inputClass.getPackage().getName();

        if(packageName != null && packageChecker.test(packageName))
            isInternalClass = isInternalClass || true;

        return isInternalClass;
    }

    public static String[] getAllInheritedClassNames(Class<?> inputClass) {
        String[] inheritedClasses;
        if (inputClass.isInterface()) {
            inheritedClasses = Arrays.stream(inputClass.getInterfaces())
                    .map(Class::getSimpleName)
                    .toArray(String[]::new);
        } else {
            Class<?> inheritedClass = inputClass.getSuperclass();
            inheritedClasses = inheritedClass != null ?
                    new String[]{inputClass.getSuperclass().getSimpleName()}
                    : null;
        }
        return inheritedClasses;
    }
}


class PopupTypeInfo {
    private boolean isPrimitive;
    private boolean isInterface;
    private boolean isEnum;

    private String name;
    private boolean isJdk;

    private final List inheritedClassNames = new ArrayList<>();

    public PopupTypeInfo setPrimitive(boolean isPrimitive) {
        this.isPrimitive = isPrimitive;
        return this;
    }

    public PopupTypeInfo setInterface(boolean isInterface) {
        this.isInterface = isInterface;
        return this;
    }

    public PopupTypeInfo setEnum(boolean isEnum) {
        this.isEnum = isEnum;
        return this;
    }

    public PopupTypeInfo setName(String name) {
        this.name = name;
        return this;
    }

    public PopupTypeInfo setJdk(boolean isJdkType) {
        this.isJdk = isJdkType;
        return this;
    }

    public PopupTypeInfo addAllInheritedClassNames(String[] inheritedClassNames) {
        if (inheritedClassNames != null) {
            this.inheritedClassNames.addAll(Arrays.stream(inheritedClassNames)
                    .collect(Collectors.toList()));
        }
        return this;
    }

    public boolean isPrimitive() {
        return isPrimitive;
    }

    public boolean isInterface() {
        return isInterface;
    }

    public boolean isEnum() {
        return isEnum;
    }

    public String getName() {
        return name;
    }

    public boolean isJdk() {
        return isJdk;
    }

    public List getInheritedClassNames() {
        return Collections.unmodifiableList(inheritedClassNames);
    }

    @Override
    public String toString() {
        return "PopupTypeInfo{" +
                "isPrimitive=" + isPrimitive +
                ", isInterface=" + isInterface +
                ", isEnum=" + isEnum +
                ", name='" + name + '\'' +
                ", isJdk=" + isJdk +
                ", inheritedClassNames=" + inheritedClassNames +
                '}';
    }
}

