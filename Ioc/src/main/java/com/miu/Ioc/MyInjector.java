package com.miu.Ioc;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.stream.Collectors;

import com.miu.Ioc.MyAutoWired;

@SuppressWarnings("rawtypes")
public class MyInjector {
    private final HashMap<Class<?>, Object> mapForObjects = new HashMap<>();
    private final String[] myAnnotations = { "MyBean" };

    public void createInstances(String packageName)
            throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        List<Class> listOfClasses = findAllClassesUsingClassLoader(packageName);

        for (Class<?> curClass : listOfClasses) {
            // System.out.println(curClass);
            for (Annotation annotation : Arrays.stream(curClass.getAnnotations()).toList()) {
                String[] splitAnnotation = annotation.toString().split("\\.");
                if (splitAnnotation.length == 0)
                    continue;

                String annotationName = splitAnnotation[splitAnnotation.length - 1];
                annotationName = annotationName.replace("()", "");

                if (Arrays.asList(myAnnotations).contains(annotationName)) {
                    if (!mapForObjects.containsKey(curClass)) {
                        Object newObject = curClass.getDeclaredConstructor().newInstance();
                        System.out.printf("Object %s is created for the class %s %n", newObject, curClass);

                        for (Field field : Arrays.stream(curClass.getDeclaredFields())
                                .filter(f -> f.isAnnotationPresent(MyAutoWired.class)).toList()) {
                            Class<?> classToCreateAnObject = field.getType();
                            Object fieldObject;

                            if (!mapForObjects.containsKey(classToCreateAnObject)) {
                                fieldObject = classToCreateAnObject.getDeclaredConstructor().newInstance();
                                mapForObjects.put(classToCreateAnObject, fieldObject);
                            } else {
                                fieldObject = mapForObjects.get(classToCreateAnObject);
                            }

                            field.setAccessible(true);
                            field.set(newObject, fieldObject);

                        }
                        mapForObjects.put(curClass, newObject);
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
            e.printStackTrace();
        }
        return null;
    }

    public Object getBean(Class clazz) throws BeanNotFoundException {
        if (!this.mapForObjects.containsKey(clazz)) {
            throw new BeanNotFoundException("Bean not found in the map!");
        }
        return this.mapForObjects.get(clazz);
    }
}
