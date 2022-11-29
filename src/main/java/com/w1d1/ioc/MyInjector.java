package com.w1d1.ioc;

import org.reflections.Reflections;
import org.reflections.scanners.TypeAnnotationsScanner;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class MyInjector {

    Set<Class<?>> myBeans;
    HashMap<String, Object> instances;

    public MyInjector() {
        instances = new HashMap<>();
        myBeans = getTypeWithMyBeans(this.getClass().getPackageName());
        fillMyBeanInstances();
    }

    public void fillMyBeanInstances() {
        for (var bean : myBeans) {
//            addBeanInstance(bean);
            checkMyBeansAndCreateInstance(bean);
        }
    }

    public void addBeanInstance(Class<?> clz) {
        Field[] fields = clz.getDeclaredFields();
        for (var field : fields) {
            boolean isAutoWired = field.isAnnotationPresent(MyAutowired.class);
            if(isAutoWired){
                checkMyBeansAndCreateInstance(field.getType());
            }
        }
    }

    public Set<Class<?>> getTypeWithMyBeans(String packageName) {
        Reflections reflections = new Reflections(packageName);
        return reflections.getTypesAnnotatedWith(MyBean.class);
    }

    private void checkMyBeansAndCreateInstance(Class<?> clz) {
        if (myBeans.contains(clz)) {
            String className = clz.getSimpleName();
            if (!instances.containsKey(className)) {
                Object obj = null;
                try {
                    obj = clz.getDeclaredConstructor().newInstance();
                } catch (InstantiationException e) {
                    throw new RuntimeException(e);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                } catch (InvocationTargetException e) {
                    throw new RuntimeException(e);
                } catch (NoSuchMethodException e) {
                    throw new RuntimeException(e);
                }
                instances.put(className, obj);
            }
        }
    }

    public <T> T getBean(Class<T> clazz) throws BeanNotFoundException {
        String className = clazz.getSimpleName();
        if (instances.containsKey(className)) {
            return (T) instances.get(className);
        } else {
            throw new BeanNotFoundException(String.format("%s is not registered as @MyBean", className));
        }
    }
}
