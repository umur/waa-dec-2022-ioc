package org.ujjwalhumagain.ioc;

import org.reflections.Reflections;
import org.reflections.scanners.Scanners;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.ujjwalhumagain.exception.BeanNotFoundException;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MyInjector {
    private static Map<String, Object> instances = new HashMap<>();

    public MyInjector(){
        inject();
    }

    private Object addToInstanceMap(Class<?> c){
        try {
            String className = c.getSimpleName();
            Object object = instances.get(className);
            if(object == null)
                object = c.getDeclaredConstructor().newInstance();
            instances.put(className, object);
            Field[] declaredFields = c.getDeclaredFields();
            for (Field field: declaredFields){
                boolean isAutowired = field.getAnnotationsByType(MyAutowired.class).length > 0;
                if(isAutowired){
                    Class<?> fieldClass = field.getType();
                    if(!fieldClass.isAnnotationPresent(MyBean.class))
                        throw new RuntimeException("Bean cannot be found");
                    Object fieldObject = addToInstanceMap(fieldClass);
                    field.set(object, fieldObject);
                }
            }
            return object;
        }catch(Exception ex){
            throw new RuntimeException("Exception during initialization of Bean: " + c.getName() + "\nError Message: " + ex.getMessage());
        }
    }
    private static Set<Class<?>> getAllClassesInPackage(String packageName){
        ConfigurationBuilder configurationBuilder = new ConfigurationBuilder();
        configurationBuilder.setScanners(Scanners.TypesAnnotated);
        configurationBuilder.setUrls(ClasspathHelper.forPackage(packageName));
        Reflections reflections = new Reflections(configurationBuilder);
        return reflections.getTypesAnnotatedWith(MyBean.class);
    }

    public void inject(){
        String packageName = MyInjector.class.getPackageName();
        Set<Class<?>> classes = getAllClassesInPackage(packageName);
        for(Class<?> c: classes){
            addToInstanceMap(c);
        }
    }

    public <T> T getBean(Class<T> clazz){
        T obj = (T) instances.get(clazz.getSimpleName());
        if(obj == null)
            throw new BeanNotFoundException("Bean cannot be found");
        return obj;
    }
}