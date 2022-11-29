package com.waalab1.ioc;
import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MyInjector {
    Map<Class<?>, Object> instances = new HashMap<>();
    public MyInjector() {
        Reflections reflections = new Reflections("com.waalab1.ioc");
        reflections.getTypesAnnotatedWith(MyBean.class).forEach(cls -> {
            try {
                Object t = cls.getDeclaredConstructor().newInstance();
                Arrays.stream(cls.getFields()).filter(c -> c.isAnnotationPresent(MyAutowired.class))
                        .toList()
                        .forEach(field -> {
                            Class<?> innerClass = field.getType();
                            try {
                                Object innerInstance = innerClass.getDeclaredConstructor().newInstance();
                                instances.put(innerClass, innerInstance);
                                field.set(t, innerInstance);
                            } catch (InstantiationException | IllegalAccessException e) {
                                throw new RuntimeException(e);
                            } catch (InvocationTargetException e) {
                                throw new RuntimeException(e);
                            } catch (NoSuchMethodException e) {
                                throw new RuntimeException(e);
                            }
                        });
                instances.put(cls, t);
            } catch (InstantiationException | IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        });
    }
    public Object getBean(Class<?> cls) throws BeanNotFoundException {
        try {
            return instances.get(cls);
        } catch (Exception e) {
            throw new BeanNotFoundException("Error");
        }
    }
}