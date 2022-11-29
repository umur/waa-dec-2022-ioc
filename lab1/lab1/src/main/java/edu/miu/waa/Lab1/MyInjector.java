package edu.miu.waa.Lab1;

import org.reflections.Reflections;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
public class MyInjector {

    // Create Map
    private Map<Class<?>, Class<?>> diMap;
    private Map<Class<?>, Object> applicationScope;

    private static MyInjector myInjector;

    private MyInjector() {
        super();
        diMap = new HashMap<>();
        applicationScope = new HashMap<>();
    }

    public static void startApplication(Class<?> mainClass) {
        try {
            if (myInjector == null) {
                myInjector = new MyInjector();
                myInjector.initFramework(mainClass);

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static <T> T getService(Class<T> classz) {
        try {
            return myInjector.getBeanInstance(classz);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    private void initFramework(Class<?> mainClass) throws IOException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<?>[] classes = ClassLoaderUtil.getClasses(mainClass.getPackage().getName());
        Reflections reflections = new Reflections(mainClass.getPackage().getName());

        Set<Class<?>> types = reflections.getTypesAnnotatedWith(MyBean.class);
        for (Class<?> implementationClass : types) {
            Class<?> [] interfaces = implementationClass.getInterfaces();

            if (interfaces.length == 0) {
                diMap.put(implementationClass, implementationClass);
            } else {
                for(Class<?> iface: interfaces) {
                    diMap.put(implementationClass, iface);
                }
            }
        }

        for (Class<?> clazz : classes) {
            if (clazz.isAnnotationPresent(MyBean.class)) {
                Object classInance = clazz.getDeclaredConstructor().newInstance();
                System.out.println("Creating application scope and put " + classInance);
                applicationScope.put(clazz, classInance);
                InjectorUtil.autowire(this, clazz, classInance);
            }
        }
    }

    private <T> T getBeanInstance(Class<T> interfaceClass) throws InstantiationException, IllegalAccessException {
        return (T) getBeanInstance(interfaceClass, null, null);

    }
    public <T> Object getBeanInstance(Class<T> interfaceClass, String fieldName, String qualifier) throws InstantiationException, IllegalAccessException {
        Class<?> implementationClass = getImplementationClass(interfaceClass, fieldName, qualifier);

        System.out.println("get Bean Instance");
        if (applicationScope.containsKey(implementationClass)) {
            System.out.println("returning 1");
            return applicationScope.get(implementationClass);
        }

        synchronized (applicationScope) {
            System.out.println("Before create service");

            Object service = implementationClass.newInstance();
            applicationScope.put(implementationClass, service);
            System.out.println("Creating service");

            return service;
        }
    }

    private Class<?> getImplementationClass(Class<?> interfaceClass, String fieldName, final String qualifier) {
        Set<Map.Entry<Class<?>, Class<?>>> implementationClass = diMap.entrySet().stream()
                .filter(entry -> entry.getValue() == interfaceClass).collect(Collectors.toSet());

        String errMsg = "";

        if (implementationClass == null || implementationClass.size() == 0) {
            errMsg = "No implentation found for interface " + interfaceClass;
        } else if (implementationClass.size() == 1) {
            Optional<Map.Entry<Class<?>, Class<?>>> optional = implementationClass.stream().findFirst();

            if (optional.isPresent()) {
                return optional.get().getKey();
            }
        }
        throw new BeanNotFoundException(errMsg);
    }
}
