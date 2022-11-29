package com.miu.ea;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.stream.Collectors;

@SuppressWarnings("rawtypes")
public class MyInjector {
    private final HashMap<Class<?>, Object> objectMap = new HashMap<>();
    public void createObjects(String packageName) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        List<Class> listOfClasses = findAllClassesUsingClassLoader(packageName);

        for (Class<?> curClass : listOfClasses) {
            for (Annotation annotation : Arrays.stream(curClass.getAnnotations()).toList()) {
                String[] splitAnnotation = annotation.toString().split("\\.");
                if (splitAnnotation.length == 0) continue;

                String annotationName = splitAnnotation[splitAnnotation.length - 1];
                annotationName = annotationName.replace("()", "");

                if ("MyBean".equals(annotationName)){
                    if (!objectMap.containsKey(curClass)){
                        Object newObject = curClass.getDeclaredConstructor().newInstance();
                        System.out.printf("Instance of class %s is created %s %n", curClass, newObject);

                        for(Field field : Arrays.stream(curClass.getDeclaredFields()).filter(f -> f.isAnnotationPresent(MyAutowired.class)).toList()) {
                            Class<?> classToCreateAnObject = field.getType();
                            Object fieldObject;

                            if (!objectMap.containsKey(classToCreateAnObject)){
                                fieldObject = classToCreateAnObject.getDeclaredConstructor().newInstance();
                                objectMap.put(classToCreateAnObject, fieldObject);
                            } else {
                                fieldObject = objectMap.get(classToCreateAnObject);
                            }

                            field.setAccessible(true);
                            field.set(newObject, fieldObject);

                        }
                        objectMap.put(curClass, newObject);
                    }
                }
            }
        }
    }

    private List<Class> findAllClassesUsingClassLoader(String packageName) {
        InputStream stream = ClassLoader.getSystemClassLoader()
                .getResourceAsStream(packageName.replaceAll("[.]", "/"));
        BufferedReader reader;
        if (stream == null) {
            return new ArrayList<>();
        }
        reader = new BufferedReader(new InputStreamReader(stream));
        return reader.lines()
                .filter(line -> line.endsWith(".class"))
                .map(line -> getClass(line, packageName))
                .collect(Collectors.toList());

    }
    private Class getClass(String className, String packageName) {
        try {
            return Class.forName(packageName + "."
                    + className.substring(0, className.lastIndexOf('.')));
        } catch (ClassNotFoundException e) {
            // handle the exception
        }
        return null;
    }

    public Object getBean(Class clazz){
        return this.objectMap.get(clazz);
    }
}
