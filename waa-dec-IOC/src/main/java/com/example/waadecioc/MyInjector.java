package com.example.waadecioc;

import org.reflections.Reflections;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MyInjector {

    Map<Class<?>, Object> map = new HashMap<>();
    public MyInjector() {
        Reflections reflections = new Reflections("com.example.waadecioc");
        reflections.getTypesAnnotatedWith(MyBean.class).forEach(myClass -> {
            try {
                Object obj = myClass.newInstance();
                Arrays.stream(myClass.getFields()).filter(fi -> fi.isAnnotationPresent(MyAutowired.class))
                        .toList()
                        .forEach(field -> {
                            Class<?> iClass = field.getType();
                            try {
                                Object iInstance = iClass.newInstance();
                                map.put(iClass, iInstance);
                                field.set(obj, iInstance);
                            } catch (InstantiationException | IllegalAccessException e) {
                                throw new RuntimeException(e);
                            }
                        });
                map.put(myClass, obj);
            } catch (InstantiationException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        });
    }
    public Object getBean(Class<?> clazz) throws BeanNotFoundException {
        try {
            return map.get(clazz);
        } catch (Exception exception) {
            throw new BeanNotFoundException("Ops Error!");
        }
    }
}
