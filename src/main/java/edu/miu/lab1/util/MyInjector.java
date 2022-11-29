package edu.miu.lab1.util;


import edu.miu.lab1.annotation.MyAutowired;
import edu.miu.lab1.exception.BeanNotFoundException;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class MyInjector {
    static Map<String, Object> autowiredFields= new HashMap<String, Object>();


    public <T> T getInstance(Class<T> clazz) throws Exception {
        T obj = (T) autowiredFields.get(clazz.getName());
        if (obj == null) {
            Constructor<?> constructor = clazz.getConstructor();
            T object = (T)constructor.newInstance();

            Field[] declaredFields = clazz.getDeclaredFields();
            injectAnnotatedFields(object, declaredFields);
            return object;
        }
        else {
            return obj;
        }
    }


    private <T> void injectAnnotatedFields(T object, Field[] declaredFields) throws Exception {
        for (Field field : declaredFields) {
            if (field.isAnnotationPresent(MyAutowired.class)) {
                field.setAccessible(true);
                Class<?> type = field.getType();
                Object innerObject = type.getDeclaredConstructor().newInstance();

                field.set(object, innerObject);
                injectAnnotatedFields(innerObject, type.getDeclaredFields());
                autowiredFields.put(type.getName(), innerObject);

            }
        }
    }


    Object getBean(Class<?> clazz) throws BeanNotFoundException {
        Object obj = autowiredFields.get(clazz.getName());
        if (obj == null) {
            throw new BeanNotFoundException("BeanNotFoundException");
        }
        return obj;

    }

}